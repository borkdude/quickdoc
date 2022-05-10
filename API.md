# quickdoc.api 


## `quickdoc`
``` clojure

(quickdoc
 [{:keys [github/repo git/branch outfile source-paths toc],
   :or {branch "main", outfile "API.md", source-paths ["src"], toc false},
   :as opts}])
```


Generate API docs. Options:
  * `:github/repo` -  a link like `https://github.com/borkdude/quickdoc`
  * `:git/branch` - branch name for source links, default to `"main"`
  * `:outfile` - file where API docs are written, or falsey if you don't need a file. Defaults to `"API.md"`
  * `:source-paths` - sources that are scanned for vars. Defaults to `"src"`.
  * `:toc` - generate table of contents. Defaults to `false`.

  Returns a map containing the generated markdown string under the key `:markdown`.

[Source](https://github.com/borkdude/quickdoc/blob/main/src/quickdoc/api.cljc#L6-L47)
