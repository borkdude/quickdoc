(ns quickdoc.api
  (:require #?(:bb [pod.borkdude.clj-kondo :as clj-kondo]
               :clj [clj-kondo.core :as clj-kondo])
            [quickdoc.impl :as impl]))

(defn quickdoc
  "Generate API docs. Options:
  * `:github/repo` -  a link like `https://github.com/borkdude/quickdoc`
  * `:git/branch` - branch name for source links, default to `\"main\"`
  * `:outfile` - file where API docs are written, or falsey if you don't need a file. Defaults to `\"API.md\"`
  * `:source-paths` - sources that are scanned for vars. Defaults to `\"src\"`.
  * `:toc` - generate table of contents. Defaults to `false`.

  Returns a map containing the generated markdown string under the key `:markdown`."
  [{:keys [github/repo
           git/branch
           outfile
           source-paths
           toc]
    :or {branch "main"
         outfile "API.md"
         source-paths ["src"]
         toc false}
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
        toc (with-out-str  (impl/print-toc nss ns-defs opts))
        docs (with-out-str
               (run! (fn [[ns-name vars]]
                       (impl/print-namespace ns-defs ns-name vars opts))
                     (sort-by first nss)))
        docs (str toc docs)]

    (when outfile
      (spit outfile docs))

    {:markdown docs}))
