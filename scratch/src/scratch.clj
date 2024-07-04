(defn heavy-bool [])

(defmacro +and
  "Logical AND of heavy-bools which evaluates to a `heavy-bool`.
  Expands to code which evaluates to the left-most `heavy-bool` value
  in the argument list, otherwise evaluates to the right-most
  value.  If the argument list is empty, evaluates explicitly to
  `+true`"
  [& rest]
  (case (count rest)
    (0) true
    (1) (first rest)
    (let [v (gensym)
          [head & tail] rest]
      `(let [~v ~head]
         (+if ~v
              (+and ~@tail)
              ~v)))))
