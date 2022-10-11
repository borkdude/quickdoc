(ns tasks
  (:require
   [clojure.string :as str]
   [quickdoc.api :refer [quickdoc]]))

(defn test-root [_]
  (quickdoc {:source-paths ["../clj-kondo/src/clj_kondo/core.clj"]
             :outfile "test/clj-kondo.md"
             :github/repo "https://github.com/clj-kondo/clj-kondo"
             :git/branch "master"
             :filename-fn (fn [filename]
                            (str/replace filename #"^\.\./clj-kondo/" ""))})
  nil)
