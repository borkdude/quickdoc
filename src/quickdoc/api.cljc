(ns quickdoc.api
  (:require [clojure.string :as str]
            [clojure.pprint :as pprint]
            [clojure.edn :as edn]
            #?(:bb [pod.borkdude.clj-kondo :as clj-kondo]
               :clj [clj-kondo.core :as clj-kondo])))

#_:clj-kondo/ignore
(defn- debug [& xs]
  (binding [*out* *err*]
    (apply println xs)))

(defn- var-filter [var]
  (let [mvar (:meta var)]
    (and (not (:no-doc mvar))
         (not (:skip-wiki mvar))
         (not (:private var))
         (not (= 'clojure.core/defrecord (:defined-by var))))))

(defn quickdoc
  "Generate API docs. Options:
  * `:github/repo` -  a link like `https://github.com/borkdude/quickdoc`
  * `:git/branch` - branch name for source links, default to `\"main\"`
  * `:outfile` - file where API docs are written, defaults to `\"API.md\"`
  * `:source-paths` - sources that are scanned for vars. Defaults to `\"src\"`.
  * `:collapse-nss` - wrap namesspaces in details tag. Defaults to `false`.
  * `:collapse-vars` - wrap vars in details tag. Defaults to `false`.
  "
  ([{:keys [github/repo
            git/branch
            outfile
            source-paths
            collapse-nss
            collapse-vars]
     :or {branch "main"
          outfile "API.md"
          source-paths ["src"]
          collapse-nss false
          collapse-vars false}}]
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
         docs
         (with-out-str
           (doseq [[ns vars] (sort-by first nss)
                   :when (let [mvar (get-in ns-defs [ns 0 :meta])]
                           (and (not (:no-doc mvar))
                                (not (:skip-wiki mvar))))
                   :let [vars (filter var-filter vars)]
                   :when (seq vars)
                   :let [ana (group-by :name vars)
                         _ (when collapse-nss (println "<details>\n\n"))
                         _ (when collapse-nss (println "<summary><code>" ns "</code></summary>\n\n"))
                         _ (println "##" ns)]
                   [_ [var]] (sort-by first ana)
                   :when (var-filter var)]
             (when collapse-vars (println "<details>\n\n"))
             (when collapse-vars (println "<summary><code>" (:name var) "</code></summary>\n\n"))
             (println "###" (format "`%s`" (:name var)))
             (when-let [arg-lists (seq (:arglist-strs var))]
               (doseq [arglist arg-lists]
                 (let [arglist (edn/read-string arglist)
                       arglist (binding [pprint/*print-miser-width* 80]
                                 (with-out-str (pprint/pprint arglist)))]
                   (println (format "<pre><code>%s</code></pre><br>" arglist)))))
             (when-let [doc (:doc var)]
               (println)
               (when (:macro var)
                 (println "Macro.\n\n"))
               (println doc))
             (println)
             (println
              (format
               "[Source](%s/blob/%s/%s#L%s-L%s)"
               repo
               branch
               (:filename var)
               (:row var)
               (:end-row var)))
             (when collapse-vars (println "</details>\n\n"))))]
     (spit outfile docs))))
