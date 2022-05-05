(ns quickdoc.api
  #?(:bb (:require [pod.borkdude.clj-kondo :as clj-kondo])
     :clj (:require [clj-kondo.core :as clj-kondo])))

(defn quickdoc
  "Generate API docs. Options:
  - `github/repo:` a link like `https://github.com/borkdude/quickdoc`
  - `git/branch:` branch name for source links, default to `\"main\"`
  - `outfile:` file where API docs are written, defaults to `\"API.md\"`
  - `source-paths:` sources that are scanned for vars. Defaults to `\"src\"`."
  [{:keys [github/repo
           git/branch
           outfile
           source-paths]
    :or {branch "main"
         outfile "API.md"
         source-paths ["src"]}}]
  (let [var-defs
        (-> (clj-kondo/run! {:lint source-paths
                             :config {:output {:analysis {:arglists true
                                                          :var-definitions {:meta [:no-doc]}}}}})
            :analysis :var-definitions)

        nss (group-by :ns var-defs)
        docs
        (with-out-str
          (doseq [[ns ana] nss
                  :let [_ (println "##" ns)]
                  var (sort-by :name ana)
                  :when (and (not (:no-doc (:meta var)))
                             (not (:private var))
                             (not (= 'clojure.core/defrecord (:defined-by var))))]
            ;; (.println System/err (:defined-by var))
            (println "###" (format "`%s`" (:name var)))
            ;; (.println System/err (keys var))
            (when-let [arg-lists (seq (:arglist-strs var))]
              (doseq [arglist arg-lists]
                (println (format "<code>%s</code><br>"  arglist))))
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
              (:end-row var)))))]
    (spit outfile docs)))
