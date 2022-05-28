(ns quickdoc.api
  "API namespace for quickdoc."
  (:require
   [clojure.string :as str]
   [quickdoc.impl :as impl]
   #?(:bb [pod.borkdude.clj-kondo :as clj-kondo]
      :clj [clj-kondo.core :as clj-kondo])))

(defn quickdoc
  "Generate API docs. Options:
  * `:github/repo` -  a link like `https://github.com/borkdude/quickdoc`
  * `:git/branch` - branch name for source links, default to `\"main\"`
  * `:outfile` - file where API docs are written, or falsey if you don't need a file. Defaults to `\"API.md\"`
  * `:source-paths` - sources that are scanned for vars. Defaults to `[\"src\"]`.
  * `:toc` - generate table of contents. Defaults to `true`.
  * `:var-links` - generate links to vars within the same namespace. Defauls to `true`.

  Returns a map containing the generated markdown string under the key `:markdown`."
  [{:keys [github/repo
           git/branch
           outfile
           source-paths
           toc var-links]
    :or {branch "main"
         outfile "API.md"
         source-paths ["src"]
         toc true
         var-links true}
    :as opts}]
  (let [ana (-> (clj-kondo/run! {:lint source-paths
                                 :config {:output {:analysis
                                                   {:arglists true
                                                    :var-definitions {:meta [:no-doc
                                                                             :skip-wiki]}
                                                    :namespace-definitions {:meta [:no-doc
                                                                                   :skip-wiki]}}}}})
                :analysis)
        var-defs (:var-definitions ana)
        ns-defs (:namespace-definitions ana)
        ns-defs (group-by :name ns-defs)
        nss (group-by :ns var-defs)
        memo (atom {})
        toc (with-out-str (impl/print-toc memo nss ns-defs opts))
        docs (with-out-str
               (run! (fn [[ns-name vars]]
                       (impl/print-namespace ns-defs ns-name vars opts))
                     (sort-by first nss)))
        docs (str toc docs)
        quoted (re-seq #" `(.*?)`([,. ])" docs)
        docs (if (:var-links opts)
               (reduce (fn [docs [raw inner suffix]]
                         (let [munged (impl/md-munge inner)]
                           (if-let [i (get @memo munged)]
                             (str/replace docs raw
                                          (format " [`%s`](#%s)%s"
                                                  inner
                                                  (str munged (if (pos? i) (str "-" i) ""))
                                                  suffix))
                             docs)))
                       docs quoted)
               docs)]
    (when outfile
      (spit outfile docs))
    {:markdown docs}))
