## quickdoc.api
### `quickdoc`
``` clojure

(quickdoc
 [{:keys
   [github/repo
    git/branch
    outfile
    source-paths
    collapse-nss
    collapse-vars],
   :or
   {branch "main",
    outfile "API.md",
    source-paths ["src"],
    collapse-nss false,
    collapse-vars false},
   :as opts}])

```


Generate API docs. Options:
  * `:github/repo` -  a link like `https://github.com/borkdude/quickdoc`
  * `:git/branch` - branch name for source links, default to `"main"`
  * `:outfile` - file where API docs are written, defaults to `"API.md"`
  * `:source-paths` - sources that are scanned for vars. Defaults to `"src"`.
  * `:collapse-nss` - wrap namesspaces in details tag. Defaults to `false`.
  * `:collapse-vars` - wrap vars in details tag. Defaults to `false`.
  

[Source](https://github.com/borkdude/quickdoc/blob/main/src/quickdoc/api.cljc#L6-L43)
<hr>
## quickdoc.impl
### `debug`
``` clojure

(debug [& xs])

```


[Source](https://github.com/borkdude/quickdoc/blob/main/src/quickdoc/impl.clj#L8-L10)
### `insert-spaces-left`
``` clojure

(insert-spaces-left [s n])

```


[Source](https://github.com/borkdude/quickdoc/blob/main/src/quickdoc/impl.clj#L23-L30)
### `mini-markdown`
``` clojure

(mini-markdown [s])

```


[Source](https://github.com/borkdude/quickdoc/blob/main/src/quickdoc/impl.clj#L19-L21)
### `print-namespace`
``` clojure

(print-namespace
 [ns-defs ns-name vars opts])

```


[Source](https://github.com/borkdude/quickdoc/blob/main/src/quickdoc/impl.clj#L71-L89)
### `print-var`
``` clojure

(print-var
 [var
  _source
  {:keys
   [github/repo
    git/branch
    collapse-vars]}])

```


[Source](https://github.com/borkdude/quickdoc/blob/main/src/quickdoc/impl.clj#L32-L69)
<hr>
