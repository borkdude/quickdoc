(ns quickdoc.api-test
  (:require
   [babashka.fs :as fs]
   [clojure.string :as str]
   [clojure.test :as t :refer [deftest is testing use-fixtures]]
   [quickdoc.api :as api]))

(use-fixtures :each (fn [f]
                      (fs/delete-tree "test/out")
                      (fs/create-dirs "test/out")
                      (f)))

(deftest source-link-test
  (testing "source link template"
    (api/quickdoc {:git/branch "main"
                   :source-uri "https://dev.azure.com/company/_git/project?path={filename}&version=GBmain&_a=contents&line={row}&lineEnd={end-row}&lineStartColumn={col}&lineEndColumn={end-col}&lineStyle=plain"
                   :toc true
                   :source-paths ["test-resources/source.clj"]
                   :outfile "test/out/API.md"})
    (let [out (slurp "test/out/API.md")]
      (is (str/includes? out
                         "https://dev.azure.com/company/_git/project?path=test-resources/source.clj&version=GBmain&_a=contents&line=3&lineEnd=3&lineStartColumn=1&lineEndColumn=12&lineStyle=plain")))))

(deftest disambiguate-between-capitalized-vars-and-namespaces-test
  (api/quickdoc {:git/branch "main"
                 :toc true
                 :source-paths ["test-resources/source.clj"]
                 :outfile "test/out/API.md"})
  (let [out (slurp "test/out/API.md")]
    (is (str/includes? out "#source/foo"))
    (is (str/includes? out "#source/foo-1"))
    (is (not (str/includes? out "#source/foo-2")))))

(deftest declare-ignored-test
  (doseq [lang ["clj" "cljs"]]
    (let [src (str "test/out/declare." lang)]
      (spit src (str/join "\n" ["(ns declare-test)"
                                ""
                                "(defn foo \"foo doc\" [])"
                                ""
                                "(declare foo)"]))
      (api/quickdoc {:git/branch "main"
                     :toc true
                     :source-paths [src]
                     :outfile "test/out/API.md"})
      (is (re-find #"(?ms)^# Table of contents.*^ .* foo doc.*^----.*^# .*declare-test.*^## .*`foo`.*^foo doc.*"
                      (slurp "test/out/API.md")) lang))))

(defn- unmatched-embedded-pattern
  "Matches `patterns` against `lines` without any backtracking if a given pattern does not match.
  Returns nil on success, unmatched pattern on failure."
  [patterns lines]
  (loop [patterns patterns
         [l & rest-l] lines]
    (let [[p & rest-p] patterns]
      (cond (not p) nil
            (not l) p
            :else
            (if (if (string? p)
                  (str/includes? l p)
                  (re-find p l))
              (recur rest-p rest-l)
              (recur patterns rest-l))))))

(defn- link-pat [text link]
  (str "\\[.*" text ".*\\]\\(" link "\\)"))

(defn- dud-link [text var-pattern]
  (if (= :wikilinks var-pattern)
    (str "\\[\\[" text "\\]\\]")
    (str ".*" text ".*")))

(deftest var-link-test
  (let [var-link1 (str/join "\n" ["(ns var-link1"
                                  "  \"var-link1 references ns [[var-link2]] and [[var1]] and [[var2]] and [[not-found]]\")"
                                  ""
                                  "(defn var1 \"var-link1/var1 references ns [[var-link1]] and [[var2]] and [[not-found]]\" [])"
                                  ""
                                  "(defn var2 \"var-link1/var2 references ns [[var-link1]] and [[var1]]\" [])"])
        var-link2 (str/join "\n" ["(ns var-link2"
                                  "  \"var-link2 references ns [[var-link1]] and [[var1]] and [[var2]]"
                                  "  and [[var-link1/var1]] and [[var-link1/var2]]\")"
                                  ""
                                  "(defn var1 \"var-link2/var1 references ns [[var-link2]] and [[var2]]\" [])"
                                  ""
                                  "(defn var2 \"var-link2/var2 references ns [[var-link1]] and [[var1]]\" [])"])]
    (doseq [[var-pattern mod-fn] [[:backticks (fn [s] (str/replace s #"(\[\[|\]\])" "`"))]
                                  [:wikilinks identity]]]
      (spit "test/out/var_link1.clj" (mod-fn var-link1))
      (spit "test/out/var_link2.clj" (mod-fn var-link2))
      (api/quickdoc {:git/branch "main"
                     :toc true
                     :var-links true
                     :var-pattern var-pattern
                     :source-paths ["test/out/var_link1.clj" "test/out/var_link2.clj"]
                     :outfile "test/out/API.md"})
      (let [out (->> (slurp "test/out/API.md")
                     str/split-lines)
            var-link1-doc (re-pattern (str "var-link1 references ns "
                                           (link-pat "var-link2" "#var-link2")
                                           " and " (link-pat "var1" "#var-link1/var1")
                                           " and " (link-pat "var2" "#var-link1/var2")
                                           " and " (dud-link "not-found" var-pattern)))
            var-link1-var1-doc (re-pattern (str "var-link1/var1 references ns "
                                                (link-pat "var-link1" "#var-link1")
                                                " and " (link-pat "var2" "#var-link1/var2")
                                                " and " (dud-link "not-found" var-pattern)))
            var-link1-var2-doc (re-pattern (str "var-link1/var2 references ns "
                                                (link-pat "var-link1" "#var-link1")
                                                " and " (link-pat "var1" "#var-link1/var1")))
            var-link2-line1-doc (re-pattern (str "var-link2 references ns "
                                                 (link-pat "var-link1" "#var-link1")
                                                 " and " (link-pat "var1" "#var-link2/var1")
                                                 " and " (link-pat "var2" "#var-link2/var2")))
            var-link2-line2-doc (re-pattern (str "and " (link-pat "var-link1/var1" "#var-link1/var1")
                                                " and " (link-pat "var-link1/var2" "#var-link1/var2")))
            var-link2-var1-doc (re-pattern (str "var-link2/var1 references ns "
                                                (link-pat "var-link2" "#var-link2")
                                                " and " (link-pat "var2" "#var-link2/var2")))
            var-link2-var2-doc (re-pattern (str "var-link2/var2 references ns "
                                                (link-pat "var-link1" "#var-link1")
                                                " and " (link-pat "var1" "#var-link2/var1")))
            expected-line-patterns [#"^# Table of contents"
                                    (re-pattern (str ".*" (link-pat "var-link1" "#var-link1")
                                                     ".* " var-link1-doc))
                                    (re-pattern (str ".*" (link-pat "var1" "#var-link1/var1")
                                                     ".* " var-link1-var1-doc))
                                    (re-pattern (str ".*" (link-pat "var2" "#var-link1/var2")
                                                     ".* " var-link1-var2-doc))
                                    (re-pattern (str ".*" (link-pat "var-link2" "#var-link2")
                                                     ".* " var-link2-line1-doc ".*" var-link2-line2-doc))
                                    (re-pattern (str ".*" (link-pat "var1" "#var-link2/var1")
                                                     ".* " var-link2-var1-doc))
                                    (re-pattern (str ".*" (link-pat "var2" "#var-link2/var2")
                                                     ".* " var-link2-var2-doc))

                                    ;; contents
                                    #"^# <a name=\"var-link1\"\>var-link1</a>"
                                    var-link1-doc
                                    #"^## <a name=\"var-link1/var1\"\>`var1`</a>"
                                    var-link1-var1-doc
                                    #"^## <a name=\"var-link1/var2\"\>`var2`</a>"
                                    var-link1-var2-doc

                                    #"^# <a name=\"var-link2\"\>var-link2</a>"
                                    var-link2-line1-doc
                                    var-link2-line2-doc
                                    #"^## <a name=\"var-link2/var1\"\>`var1`</a>"
                                    #"^## <a name=\"var-link2/var2\"\>`var2`</a>"]]
        (is (= nil (unmatched-embedded-pattern expected-line-patterns out))
            "unmatched pattern is nil")))))

(deftest namespaced-keyword-in-arglist-test
  (testing "namespaced keyword fallback in :or destructuring (issue #52)"
    (let [{:keys [markdown]} (api/quickdoc {:git/branch "main"
                                            :toc true
                                            :source-paths ["test-resources/namespaced_kw.clj"]})]
      (is (str/includes? markdown "(bar {:keys [baz], :or {baz ::my-kw}})"))
      (is (not (str/includes? markdown "\"bar"))))))

(deftest multiline-backtick-var-link-test
  (testing "var in backticks is linked even when preceded by multiline backtick expression (issue #42)"
    (let [{:keys [markdown]} (api/quickdoc {:git/branch "main"
                                            :toc true
                                            :source-paths ["test-resources/multiline_backtick.clj"]})]
      (is (str/includes? markdown "[`create-temp-dir`](#multiline-backtick/create-temp-dir)"))
      (is (str/includes? markdown "[`delete-tree`](#multiline-backtick/delete-tree)"))
      (is (not (str/includes? markdown "[`, then cleans up. See `]"))))))
