(ns quickdoc.impl
  {:no-doc true}
  (:require [clojure.edn :as edn]
            [clojure.string :as str]))

(defn debug [& xs]
  (binding [*out* *err*]
    (apply println xs)))

(defn- var-filter [var]
  (let [mvar (:meta var)]
    (and (not (:no-doc mvar))
         (not (:skip-wiki mvar))
         (not (:private var))
         (not (= 'clojure.core/defrecord (:defined-by var))))))

(defn mini-markdown [s]
  (str/replace s #"`(.*?)`" (fn [[_ s]]
                              (format "<code>%s</code>" s))))

(defn print-var [var {:keys [github/repo git/branch collapse-vars]}]
  (when (var-filter var)
    (when collapse-vars (println "<details>\n\n"))
    (when collapse-vars
      (println (str "<summary><code>" (:name var) "</code>"
                    (when-let [first-line (some-> (:doc var) (str/split-lines) (first))]
                      (let [first-sentence (-> (str/split first-line #"\. ") first)]
                        (str " - " (mini-markdown (subs first-sentence 0 (min (count first-sentence) 80)))))))
               "</summary>\n\n"))
    (println "###" (format "`%s`" (:name var)))
    (when-let [arg-lists (seq (:arglist-strs var))]
      (doseq [arglist arg-lists]
        (let [arglist (str/replace arglist ":or" "\n  :or")
              #_#_arglist (edn/read-string arglist)
              #_#_arglist (binding [#_#_pprint/*print-miser-width* 80]
                            (with-out-str (pprint/pprint arglist)))]
          (println (format "```` clojure\n%s\n````<br>" arglist)))))
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
    (when collapse-vars (println "</details>\n\n"))))

(defn print-namespace [ns-defs ns-name vars opts]
  (let [mns (get-in ns-defs [ns-name 0 :meta])]
    (when (and (not (:no-doc mns))
               (not (:skip-wiki mns)))
      (when-let [vars (seq (filter var-filter vars))]
        (let [ana (group-by :name vars)
              collapse-nss (:collapse-nss opts)]
          (when collapse-nss (println "<details>\n\n"))
          (when collapse-nss (println "<summary><code>" ns-name "</code></summary>\n\n"))
          (println "##" ns-name)
          (run! (fn [[_ [var]]]
                  (print-var var opts))
                (sort-by first ana))
          (when collapse-nss (println "</details>\n\n"))
          (println "<hr>"))))))
