(ns runner
  (:require
   [clojure.test]
   [quickdoc.api-test]))

(defn run-tests [_]
  (let [{:keys [fail error]} (clojure.test/run-tests 'quickdoc.api-test)]
    (when (pos? (+ fail error))
      (System/exit 1))))
