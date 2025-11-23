(ns quickdoc.impl-test
  (:require
    [clojure.string :as str]
    [clojure.test :as t :refer [deftest is]]
    [quickdoc.impl :refer [var-summary var-source]]))

(deftest var-summary-test
  (let [opts {}
        ns->vars {}
        current-ns nil]
    (is (= nil (var-summary opts ns->vars current-ns {})))
    (is (= nil (var-summary opts ns->vars current-ns {:doc ""})))
    (is (= nil (var-summary opts ns->vars current-ns {:doc " "})))
    (is (= nil (var-summary opts ns->vars current-ns {:doc " \n\t \r"})))
    (is (= "3 5 7 9." (var-summary opts ns->vars current-ns {:doc "  3\n5\t7 \n9 "})))
    (is (= "3 5 7 9." (var-summary opts ns->vars current-ns {:doc "\n 3\n5\t7 \n9 "})))
    (is (= "." (var-summary opts ns->vars current-ns {:doc ". 345"})))
    (is (=  "12 45." (var-summary opts ns->vars current-ns {:doc "12 45"})))

    (is (= "1 3." (var-summary opts ns->vars current-ns {:doc "1 3. 6. 9."})))
    (is (= "1 3.5 7." (var-summary opts ns->vars current-ns {:doc "1 3.5 7"}) ))
    (is (= "1 3.5 7." (var-summary opts ns->vars current-ns {:doc "1 3.5 7."}) ))
    (is (= "1 3.5." (var-summary opts ns->vars current-ns {:doc "1 3.5. 8."}) ))
    (is (= "1 <code>4</code>." (var-summary opts ns->vars current-ns {:doc "1 `4`. 8"})))))

(deftest var-source-test
  (is (= "https://github.com/babashka/process/blob/master/src/babashka/process.cljc#L158-L163"
         (var-source
           {:filename "src/babashka/process.cljc"
            :row      158
            :end-row  163}
           {:github/repo "https://github.com/babashka/process"
            :git/branch  "master"})))
  (is (= "https://github.com/foo/blob/main/prefix/src/bar/baz.clj#L1-L10"
         (var-source
           {:filename "src/bar/baz.clj"
            :row      1
            :end-row  10}
           {:github/repo         "https://github.com/foo"
            :git/branch          "main"
            :filename-add-prefix "prefix/"})))
  (is (= "https://github.com/foo/blob/main/src/bar/baz.clj#L1-L10"
         (var-source
           {:filename "extra/src/bar/baz.clj"
            :row      1
            :end-row  10}
           {:github/repo         "https://github.com/foo"
            :git/branch          "main"
            :filename-remove-prefix "extra/"})))
  (is (= "https://github.com/foo/blob/main/src/bar/baz.clj#L1-L10"
         (var-source
           {:filename "SRC/BAR/BAZ.CLJ"
            :row      1
            :end-row  10}
           {:github/repo "https://github.com/foo"
            :git/branch  "main"
            :filename-fn str/lower-case})))
  (is (= "http://example.com/main/src/bar/baz.clj;1,2-10,4"
         (var-source
           {:filename "src/bar/baz.clj"
            :row      1
            :col      2
            :end-row  10
            :end-col  4}
           {:source-uri "{repo}{branch}/{filename};{row},{col}-{end-row},{end-col}"
            :github/repo "http://example.com/"
            :git/branch "main"}))))
