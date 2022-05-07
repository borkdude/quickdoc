## quickdoc.api
### `quickdoc`
> <code>[{:keys [github/repo git/branch outfile source-paths collapse-nss collapse-vars] :or {branch "main" outfile "API.md" source-paths ["src"] collapse-nss false collapse-vars false} :as opts}]</code><br>

Generate API docs. Options:
  * `:github/repo` -  a link like `https://github.com/borkdude/quickdoc`
  * `:git/branch` - branch name for source links, default to `"main"`
  * `:outfile` - file where API docs are written, defaults to `"API.md"`
  * `:source-paths` - sources that are scanned for vars. Defaults to `"src"`.
  * `:collapse-nss` - wrap namesspaces in details tag. Defaults to `false`.
  * `:collapse-vars` - wrap vars in details tag. Defaults to `false`.
  

[Source](https://github.com/borkdude/quickdoc/blob/main/src/quickdoc/api.cljc#L6-L43)
<hr>
