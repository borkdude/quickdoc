(ns quickdoc.api
  (:require #?(:bb [pod.borkdude.clj-kondo :as clj-kondo]
               :clj [clj-kondo.core :as clj-kondo])
            [quickdoc.impl :as impl]))

(defn quickdoc
  "Generate API docs. Options:
  * `:github/repo` -  a link like `https://github.com/borkdude/quickdoc`
  * `:git/branch` - branch name for source links, default to `\"main\"`
  * `:outfile` - file where API docs are written, defaults to `\"API.md\"`
  * `:source-paths` - sources that are scanned for vars. Defaults to `\"src\"`.
  * `:collapse-nss` - wrap namesspaces in details tag. Defaults to `false`.
  * `:collapse-vars` - wrap vars in details tag. Defaults to `false`.
  "
  [{:keys [github/repo
           git/branch
           outfile
           source-paths
           collapse-nss
           collapse-vars]
    :or {branch "main"
         outfile "API.md"
         source-paths ["src"]
         collapse-nss false
         collapse-vars false}
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
    (spit outfile docs)))
