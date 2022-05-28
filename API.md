# Table of contents
-  [`quickdoc.api`](#quickdocapi)  - API namespace for quickdoc.
    -  [`quickdoc`](#quickdoc) - Generate API docs
# quickdoc.api 


API namespace for quickdoc.



## `quickdoc`
``` clojure

(quickdoc
 [{:keys [github/repo git/branch outfile source-paths toc],
   :or {branch "main", outfile "API.md", source-paths ["src"], toc true},
   :as opts}])
```


Generate API docs. Options:
  * `:github/repo` -  a link like `https://github.com/borkdude/quickdoc`
  * `:git/branch` - branch name for source links, default to `"main"`
  * `:outfile` - file where API docs are written, or falsey if you don't need a file. Defaults to `"API.md"`
  * `:source-paths` - sources that are scanned for vars. Defaults to `["src"]`.
  * `:toc` - generate table of contents. Defaults to `true`.

  Returns a map containing the generated markdown string under the key `:markdown`.

[Source](https://github.com/borkdude/quickdoc/blob/main/src/quickdoc/api.cljc#L7-L48)
