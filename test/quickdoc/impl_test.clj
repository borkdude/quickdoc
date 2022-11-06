(ns quickdoc.impl-test
  (:require
   [clojure.test :as t :refer [deftest is]]
   [quickdoc.impl :refer [var-summary var-source]]))

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

(deftest var-source-test
  (is (= "https://github.com/babashka/process/blob/master/src/babashka/process.cljc#L158-L163"
         (var-source
           {:filename "src/babashka/process.cljc"
            :row      158
            :end-row  163}
           {:github/repo "https://github.com/babashka/process"
            :git/branch  "master"})))
  (is (= "https://github.com/foo/blob/main/my-projects/project3/src/bar/baz.clj#L1-L10"
         (var-source
           {:filename "src/bar/baz.clj"
            :row      1
            :end-row  10}
           {:github/repo "https://github.com/foo"
            :git/branch  "main"
            :git/path    "/my-projects/project3"})))
  (is (= "http://example.com/main/my-project/src/bar/baz.clj;1,2-10,4"
         (var-source
           {:filename "src/bar/baz.clj"
            :row      1
            :col      2
            :end-row  10
            :end-col  4}
           {:source-uri "{repo}{branch}{path}/{filename};{row},{col}-{end-row},{end-col}"
            :github/repo "http://example.com/"
            :git/branch "main"
            :git/path   "/my-project"}))))