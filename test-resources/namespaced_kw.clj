(ns namespaced-kw)

(defn bar
  "prints baz"
  [{:keys [baz]
    :or   {baz ::my-kw}}]
  (println baz))
