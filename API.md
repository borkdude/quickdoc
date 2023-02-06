# Table of contents
-  [`quickdoc.api`](#quickdoc.api)  - API namespace for quickdoc.
    -  [`quickdoc`](#quickdoc.api/quickdoc) - Generate API docs.

-----
# <a name="quickdoc.api">quickdoc.api</a>


API namespace for quickdoc.




## <a name="quickdoc.api/quickdoc">`quickdoc`</a><a name="quickdoc.api/quickdoc"></a>
``` clojure

(quickdoc opts)
```


Generate API docs. Options:
  * `:github/repo` -  a link like `https://github.com/borkdude/quickdoc`
  * `:git/branch` - branch name for source links, default to `"main"`
  * `:source-uri` - source link template. Supports `{row}`, `{end-row}`, `{col}`, `{end-col}`, `{filename}`, `{branch}`, `{path}`, `{repo}`.
  * `:outfile` - file where API docs are written, or falsey if you don't need a file. Defaults to `"API.md"`
  * `:source-paths` - sources that are scanned for vars. Defaults to `["src"]`.
  * `:toc` - generate table of contents. Defaults to `true`.
  * `:var-links` - generate links to vars within the same namespace. Defauls to `true`.
  * `:var-pattern` - detecting vars for linking, either `:backticks` (default) or `:wikilinks` (double brackets)
  * `:overrides` - overrides in the form `{namespace {:no-doc true var {:no-doc true :doc ...}}}`
  * `:filename-add-prefix` - add a prefix to the filename for source links.
  * `:filename-remove-prefix` - remove a prefix from the filename for source links.
  * `:filename-fn` - transformation of filename before it is rendered to markdown, e.g. for source links.

  Returns a map containing the generated markdown string under the key `:markdown`.
<br><sub><a href="https://github.com/borkdude/quickdoc/blob/main/src/quickdoc/api.cljc#L17-L80">source</a></sub>
