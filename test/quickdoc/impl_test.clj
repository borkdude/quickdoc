(ns quickdoc.impl-test
  (:require
   [clojure.test :as t :refer [deftest is]]
   [quickdoc.impl :refer [var-summary]]))

(deftest var-summary-test
  (is (= nil (var-summary {})))
  (is (= nil (var-summary {:doc ""})))
  (is (= nil (var-summary {:doc " "})))
  (is (= nil (var-summary {:doc " \n\t \r"})))
  (is (= "3 5 7 9." (var-summary {:doc "  3\n5\t7 \n9 "})))
  (is (= "3 5 7 9." (var-summary {:doc "\n 3\n5\t7 \n9 "})))
  (is (= "." (var-summary {:doc ". 345"})))
  (is (=  "12 45." (var-summary {:doc "12 45"})))

  (is (= "1 3." (var-summary {:doc "1 3. 6. 9."})))
  (is (= "1 3.5 7." (var-summary {:doc "1 3.5 7"}) ))
  (is (= "1 3.5 7." (var-summary {:doc "1 3.5 7."}) ))
  (is (= "1 3.5." (var-summary {:doc "1 3.5. 8."}) ))
  (is (= "1 <code>4</code>." (var-summary {:doc "1 `4`. 8"}))))
