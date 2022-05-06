## quickdoc.api
### `quickdoc`
<code>[x y]</code><br>
<code>[{:keys [github/repo git/branch outfile source-paths] <br><span>&nbsp;&nbsp;</span>:or {branch "main" outfile "API.md" source-paths ["src"]}}]</code><br>

Generate API docs. Options:
  * `:github/repo` -  a link like `https://github.com/borkdude/quickdoc`
  * `:git/branch` - branch name for source links, default to `"main"`
  * `:outfile` - file where API docs are written, defaults to `"API.md"`
  * `:source-paths` - sources that are scanned for vars. Defaults to `"src"`.

[Source](https://github.com/borkdude/quickdoc/blob/main/src/quickdoc/api.cljc#L11-L71)
