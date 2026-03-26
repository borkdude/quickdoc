(ns multiline-backtick)

(defn create-temp-dir
  "Creates a temp dir."
  [])

(defn delete-tree
  "Deletes a tree."
  [])

(defn with-temp-dir
  "Evaluates body with binding-name bound to the result of `(create-temp-dir
  options)`, then cleans up. See `create-temp-dir` for valid `options`.

  The directory will be removed with `delete-tree` on exit from the scope."
  [])
