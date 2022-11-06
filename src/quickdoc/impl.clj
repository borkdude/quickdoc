(ns quickdoc.impl
  {:no-doc true}
  (:require
   [clojure.edn :as edn]
   [clojure.pprint :as pprint]
   [clojure.string :as str]))

(defn debug [& xs]
  (binding [*out* *err*]
    (apply println xs)))

(defn- var-filter [var]
  (let [mvar (merge (:meta var) var)]
    (and (not (:no-doc mvar))
         (not (:skip-wiki mvar))
         (not (:private var))
         (not (= 'clojure.core/defrecord (:defined-by var))))))

(defn mini-markdown [s]
  (str/replace s #"`(.*?)`" (fn [[_ s]]
                              (format "<code>%s</code>" s))))

(defn var-summary
  "Returns the first sentence of the var's DOC'umentation, if any

  It collapses all continuous whitespaces to a single space."
  [{:keys [doc] :as _var}]
  (when-not (str/blank? doc)
    (let [norm (-> (str/replace doc #"\s+" " ")
                   str/trim)
          sen (or (some->> (re-find #"(.*?\.)[\s]|(.*?\.)$" norm)
                           rest
                           (some identity))
                  (str norm "."))]
      (mini-markdown sen))))

(defn var-source [var {:keys [github/repo git/branch
                              source-uri
                              filename-fn]
                       :or {filename-fn identity}}]
  (let [filename (filename-fn (:filename var))]
    (cond repo
          (format
           "%s/blob/%s/%s#L%s-L%s"
           repo
           branch
           filename
           (:row var)
           (:end-row var))
          source-uri
          (->
           source-uri
           (str/replace "{filename}" filename)
           (str/replace "{branch}" branch)
           (str/replace "{row}" (str (:row var)))
           (str/replace "{col}" (str (:col var)))
           (str/replace "{end-row}" (str (:end-row var)))
           (str/replace "{end-col}" (str (:end-col var)))))))

(defn print-docstring [ns->vars current-ns docstring opts]
  (println
    (if-some [var-regex (:var-regex opts)]
      (reduce (fn [docstring [raw inner]]
                (cond
                  ;; Looks qualified
                  (str/includes? inner "/")
                  (let [split (str/split inner #"/")]
                    (if (and (= (count split) 2)
                             (get-in ns->vars [(symbol (first split))
                                               (symbol (second split))]))
                      (str/replace docstring raw (format "[`%s`](#%s)" inner inner))
                      docstring))
                  ;; Not qualified, maybe a namespace
                  (contains? ns->vars (symbol inner))
                  (str/replace docstring raw (format "[`%s`](#%s)" inner inner))
                  ;; Not qualified, maybe a var in the current namespace
                  (get-in ns->vars [current-ns (symbol inner)])
                  (str/replace docstring raw (format "[`%s`](#%s/%s)" inner current-ns inner))
                  ;; Just regular markdown backticks
                  :else
                  docstring))
              docstring
              (re-seq var-regex docstring))
      docstring)))

(defn print-var [ns->vars ns-name var _source {:keys [collapse-vars] :as opts}]
  (println)
  (when (var-filter var)
    (when collapse-vars (println "<details>\n\n"))
    (when collapse-vars
      (println (str "<summary><code>" (:name var) "</code>"
                    (when-let [summary (var-summary var)]
                      (str " - " summary)))
               "</summary>\n\n"))
    (print "##" (format "<a name=\"%s/%s\">`%s`</a>"
                          ns-name
                          (:name var)
                          (:name var)))
    (println (format " [:page_facing_up:](%s)"
                     (var-source var opts)))
    (println (format "<a name=\"%s/%s\"></a>"
                     ns-name
                     (:name var)))
    (when-let [arg-lists (or (when-let [quoted-arglists (-> var :meta :arglists)]
                               (if (and (seq? quoted-arglists)
                                        (= 'quote (first quoted-arglists)))
                                 (second quoted-arglists)
                                 quoted-arglists))
                             (seq (:arglist-strs var)))]
      (println "``` clojure\n")
      (doseq [arglist arg-lists]
        (let [arglist (try (edn/read-string arglist)
                           (catch Exception _ arglist))
              arglist (if (coll? arglist)
                        (cons (:name var) arglist)
                        (list (str (:name var) arglist)))
              arglist (binding [pprint/*print-miser-width* nil
                                pprint/*print-right-margin* 120]
                    (with-out-str (pprint/pprint arglist)))
            ]
        (print arglist)))
      (println "```\n"))
  (when-let [doc (:doc var)]
    (println)
    (when (:macro var)
      (println "Macro.\n\n"))
    (print-docstring ns->vars ns-name doc opts)
    (when collapse-vars (println "</details>\n\n")))))

(defn print-namespace [ns-defs ns->vars ns-name vars opts overrides]
  (let [ns (get-in ns-defs [ns-name 0])
        filename (:filename ns)
        source (try (slurp filename)
                    (catch Exception _ nil))
        mns (get ns :meta)
        overriden-ns (get overrides ns-name)
        mns (merge mns overriden-ns)]
    (when (and (not (:no-doc mns))
               (not (:skip-wiki mns)))
      (println)
      (println "-----")
      (let [var-map (zipmap (map :name vars) vars)
            var-map (merge-with merge var-map overriden-ns)]
        (when-let [vars (seq (filter var-filter (vals var-map)))]
          (let [ana (group-by :name vars)
                collapse-nss (:collapse-nss opts)]
            (when collapse-nss (println "<details>\n\n"))
            (when collapse-nss (println "<summary><code>" ns-name "</code></summary>\n\n"))
            (println (format "# <a name=\"%s\">%s</a>\n\n" ns-name ns-name))
            (when-let [doc (:doc ns)]
              (print-docstring ns->vars ns-name doc opts))
            (println "\n\n")
            (run! (fn [[_ vars]]
                    (let [var (last vars)]
                      (print-var ns->vars ns-name var source opts)))
                  (sort-by first ana))
            (when collapse-nss (println "</details>\n\n"))))))))

(defn print-toc* [nss ns-defs _opts overrides]
  (println "# Table of contents")
  (doseq [[ns-name vars] (sort-by first nss)]
    (let [ns (get-in ns-defs [ns-name 0])
          overriden-ns (get overrides ns-name)
          mns (get ns :meta)
          mns (merge mns overriden-ns)]
      (when (and (not (:no-doc mns))
                 (not (:skip-wiki mns)))
        (println "- " (format "[`%s`](#%s) %s"
                              ns-name
                              ns-name
                              (str (when-let [summary (var-summary ns)]
                                     (str " - " summary)))))
        (let [vars (group-by :name vars)
              vars (sort-by first vars)]
          (doseq [[var-name var-infos] vars]
            (let [v (last var-infos)]
              (when (var-filter (merge v (get overriden-ns var-name)))
                (println
                 "    - "
                 (str (format "[`%s`](#%s)"
                              var-name
                              (str ns-name "/" var-name))
                      (when-let [summary (var-summary v)]
                        (str " - " summary))))))))))))

(defn print-toc [nss ns-defs opts overrides]
  (when (:toc opts)
    (print-toc* nss ns-defs opts overrides)))
