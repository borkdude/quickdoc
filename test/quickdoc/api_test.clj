(ns quickdoc.api-test
  (:require
   [clojure.string :as str]
   [clojure.test :as t :refer [deftest is testing]]
   [quickdoc.api :as api]))

(deftest source-link-test
  (testing "source link template"
    (api/quickdoc {:git/branch "main"
                   :source-uri "https://dev.azure.com/company/_git/project?path={filename}&version=GBmain&_a=contents&line={row}&lineEnd={end-row}&lineStartColumn={col}&lineEndColumn={end-col}&lineStyle=plain"
                   :toc true
                   :source-paths ["test-resources/source.clj"]
                   :outfile "test/out/API.md"})
    (let [out (slurp "test/out/API.md")]
      (is (str/includes? out
                         "https://dev.azure.com/company/_git/project?path=test-resources/source.clj&version=GBmain&_a=contents&line=3&lineEnd=5&lineStartColumn=1&lineEndColumn=6&lineStyle=plain")))))

(deftest disambiguate-between-capitalized-vars-and-namespaces-test
  (api/quickdoc {:git/branch "main"
                 :source-uri "https://dev.azure.com/company/_git/project?path={filename}&version=GBmain&_a=contents&line={row}&lineEnd={end-row}&lineStartColumn={col}&lineEndColumn={end-col}&lineStyle=plain"
                 :toc true
                 :source-paths ["test-resources/source.clj"]
                 :outfile "test/out/API.md"})
  (let [out (slurp "test/out/API.md")]
    out))
