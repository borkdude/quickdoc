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

(comment
  ;; expected outcomes
  [(= (var-summary {}) nil)
   (= (var-summary {:doc ""}) nil)
   (= (var-summary {:doc " "}) nil)
   (= (var-summary {:doc " \n\t \r"}) nil)
   (= (var-summary {:doc "  3\n5\t7 \n9 "}) "3 5 7 9.")
   (= (var-summary {:doc "\n 3\n5\t7 \n9 "}) "3 5 7 9.")
   (= (var-summary {:doc ". 345"}) ".")
   (= (var-summary {:doc "12 45"}) "12 45.")

   (= (var-summary {:doc "1 3. 6. 9."}) "1 3.")
   (= (var-summary {:doc "1 3.5 7"}) "1 3.5 7.")
   (= (var-summary {:doc "1 3.5 7."}) "1 3.5 7.")
   (= (var-summary {:doc "1 3.5. 8."}) "1 3.5.")
   (= (var-summary {:doc "1 `4`. 8"}) "1 <code>4</code>.")]
  ;;
  )

(defn var-source [var {:keys [github/repo git/branch
                              source-uri]}]
  (cond repo
        (format
         "<sub>[source](%s/blob/%s/%s#L%s-L%s)</sub>"
         repo
         branch
         (:filename var)
         (:row var)
         (:end-row var))
        source-uri
        (->
         source-uri
         (str/replace "{filename}" (:filename var))
         (str/replace "{branch}" branch)
         (str/replace "{row}" (str (:row var)))
         (str/replace "{col}" (str (:col var)))
         (str/replace "{end-row}" (str (:end-row var)))
         (str/replace "{end-col}" (str (:end-col var))))))

(defn print-var [var _source {:keys [collapse-vars] :as opts}]
  (when (var-filter var)
    (when collapse-vars (println "<details>\n\n"))
    (when collapse-vars
      (println (str "<summary><code>" (:name var) "</code>"
                    (when-let [summary (var-summary var)]
                      (str " - " summary)))
               "</summary>\n\n"))
    (println "##" (format "`%s`" (:name var)))
    (when-let [arg-lists (seq (:arglist-strs var))]
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
      (println doc)
      (print "<br>"))
    (println (var-source var opts))
    (when collapse-vars (println "</details>\n\n"))))

(defn with-idx [s memo]
  (let [v (swap! memo update s (fnil inc -1))
        c (get v s)]
    (if (zero? c)
      s
      (str s "-" c))))

(defn print-namespace [ns-defs ns-name vars opts overrides]
  (let [ns (get-in ns-defs [ns-name 0])
        filename (:filename ns)
        source (try (slurp filename)
                    (catch Exception _ nil))
        mns (get ns :meta)
        overriden-ns (get overrides ns-name)
        mns (merge mns overriden-ns)]
    (when (and (not (:no-doc mns))
               (not (:skip-wiki mns)))
      (let [var-map (zipmap (map :name vars) vars)
            var-map (merge-with merge var-map overriden-ns)]
        (when-let [vars (seq (filter var-filter (vals var-map)))]
          (let [ana (group-by :name vars)
                collapse-nss (:collapse-nss opts)]
            (when collapse-nss (println "<details>\n\n"))
            (when collapse-nss (println "<summary><code>" ns-name "</code></summary>\n\n"))
            (println "#" ns-name "\n\n")
            (when-let [doc (:doc ns)]
              (println doc))
            (println "\n\n")
            (run! (fn [[_ vars]]
                    (let [var (last vars)]
                      (print-var var source opts)))
                  (sort-by first ana))
            (when collapse-nss (println "</details>\n\n"))))))))

(defn md-munge [s]
  (str/replace s #"[\*\.!]" ""))

(defn print-toc* [memo nss ns-defs _opts overrides]
  (println "# Table of contents")
  (doseq [[ns-name vars] (sort-by first nss)]
    (let [ns (get-in ns-defs [ns-name 0])
          overriden-ns (get overrides ns-name)
          mns (get ns :meta)
          mns (merge mns overriden-ns)]
      (when (and (not (:no-doc mns))
                 (not (:skip-wiki mns)))
        (println "- " (format "[`%s`](#%s) %s" ns-name
                              (str (with-idx (md-munge ns-name) memo))
                              (str (when-let [summary (var-summary ns)]
                                     (str " - " summary)))))
        (let [vars (group-by :name vars)
              vars (sort-by first vars)]
          (doseq [[var-name var-infos] vars]
            (let [v (last var-infos)]
              (when (var-filter (merge v (get overriden-ns var-name)))
                (println
                 "    - "
                 (str (format "[`%s`](#%s)" var-name (with-idx (md-munge var-name) memo))
                      (when-let [summary (var-summary v)]
                        (str " - " summary))))))))))))

(defn print-toc [memo nss ns-defs opts overrides]
  (if (:toc opts)
    (print-toc* memo nss ns-defs opts overrides)
    (when (:var-links opts)
      ;; we run toc anyway to populate memo, but suppress output
      (with-out-str (print-toc* memo nss ns-defs opts overrides)))))
