# Table of contents
-  [`quickdoc.api`](#quickdoc.api)  - API namespace for quickdoc.
    -  [`quickdoc`](#quickdoc.api/quickdoc) - Generate API docs.

-----
# <a name="quickdoc.api">quickdoc.api</a>


API namespace for quickdoc.




## <a name="quickdoc.api/quickdoc">`quickdoc`</a> [:page_facing_up:](https://github.com/borkdude/quickdoc/blob/main/src/quickdoc/api.cljc#L16-L76)
<a name="quickdoc.api/quickdoc"></a>
``` clojure

(quickdoc opts)
```


Generate API docs. Options:
  * `:github/repo` -  a link like `https://github.com/borkdude/quickdoc`
  * `:git/branch` - branch name for source links, default to `"main"`
  * `:source-uri` - source link template. Supports `{row}`, `{end-row}`, `{col}`, `{end-col}`, `{filename}`, `{branch}`.
  * `:outfile` - file where API docs are written, or falsey if you don't need a file. Defaults to `"API.md"`
  * `:source-paths` - sources that are scanned for vars. Defaults to `["src"]`.
  * `:toc` - generate table of contents. Defaults to `true`.
  * `:var-links` - generate links to vars within the same namespace. Defauls to `true`.
  * `:var-pattern` - detecting vars for linking, either `:backticks` (default) or `:wikilinks` (double brackets)
  * `:overrides` - overrides in the form `{namespace {:no-doc true var {:no-doc true :doc ...}}}`
  * `:filename-fn` - transformation of filename before it is rendered to markdown, e.g. for source links.

  Returns a map containing the generated markdown string under the key `:markdown`.
