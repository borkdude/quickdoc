# quickdoc.api 


## `quickdoc`
``` clojure

(quickdoc
 [{:keys [github/repo git/branch outfile source-paths],
   :or {branch "main", outfile "API.md", source-paths ["src"], toc false},
   :as opts}])
```


Generate API docs. Options:
  * `:github/repo` -  a link like `https://github.com/borkdude/quickdoc`
  * `:git/branch` - branch name for source links, default to `"main"`
  * `:outfile` - file where API docs are written, defaults to `"API.md"`
  * `:source-paths` - sources that are scanned for vars. Defaults to `"src"`.
  * `:toc` - generate table of contents. Defaults to `false`.

[Source](https://github.com/borkdude/quickdoc/blob/main/src/quickdoc/api.cljc#L6-L40)
