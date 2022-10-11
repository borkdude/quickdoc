# Table of contents
-  [`aaaa-this-has-to-be-first.pprint`](#aaaa-this-has-to-be-first.pprint) 
    -  [`new-table-ize`](#aaaa-this-has-to-be-first.pprint/new-table-ize)
    -  [`new-write`](#aaaa-this-has-to-be-first.pprint/new-write)
    -  [`patched?`](#aaaa-this-has-to-be-first.pprint/patched?)
-  [`clj-kondo.core`](#clj-kondo.core) 
    -  [`config-hash`](#clj-kondo.core/config-hash) - Return the hash of the provided clj-kondo config.
    -  [`merge-configs`](#clj-kondo.core/merge-configs) - Returns the merged configuration of c1 with c2.
    -  [`print!`](#clj-kondo.core/print!) - Prints the result from <code>run!</code> to <code>*out*</code>.
    -  [`resolve-config`](#clj-kondo.core/resolve-config) - Returns the configuration for <code>cfg-dir</code> merged with home, clj-kondo default configs and optional <code>config</code> if provided.
    -  [`run!`](#clj-kondo.core/run!) - Takes a map with: - <code>:lint</code>: a seqable of files, directories and/or classpaths to lint.
-  [`clj-kondo.hooks-api`](#clj-kondo.hooks-api) 
    -  [`*reload*`](#clj-kondo.hooks-api/*reload*)
    -  [`coerce`](#clj-kondo.hooks-api/coerce)
    -  [`keyword-node`](#clj-kondo.hooks-api/keyword-node)
    -  [`keyword-node?`](#clj-kondo.hooks-api/keyword-node?)
    -  [`list-node`](#clj-kondo.hooks-api/list-node)
    -  [`list-node?`](#clj-kondo.hooks-api/list-node?)
    -  [`macroexpand`](#clj-kondo.hooks-api/macroexpand)
    -  [`map-node`](#clj-kondo.hooks-api/map-node)
    -  [`map-node?`](#clj-kondo.hooks-api/map-node?)
    -  [`ns-analysis`](#clj-kondo.hooks-api/ns-analysis) - Return any cached analysis for the namespace identified by ns-sym.
    -  [`parse-string`](#clj-kondo.hooks-api/parse-string)
    -  [`pprint`](#clj-kondo.hooks-api/pprint)
    -  [`prewalk`](#clj-kondo.hooks-api/prewalk)
    -  [`reg-finding!`](#clj-kondo.hooks-api/reg-finding!)
    -  [`reg-keyword!`](#clj-kondo.hooks-api/reg-keyword!)
    -  [`sexpr`](#clj-kondo.hooks-api/sexpr)
    -  [`string-node`](#clj-kondo.hooks-api/string-node)
    -  [`string-node?`](#clj-kondo.hooks-api/string-node?)
    -  [`tag`](#clj-kondo.hooks-api/tag)
    -  [`token-node`](#clj-kondo.hooks-api/token-node)
    -  [`token-node?`](#clj-kondo.hooks-api/token-node?)
    -  [`vector-node`](#clj-kondo.hooks-api/vector-node)
    -  [`vector-node?`](#clj-kondo.hooks-api/vector-node?)
    -  [`walk`](#clj-kondo.hooks-api/walk)
-  [`clj-kondo.impl.analysis.java`](#clj-kondo.impl.analysis.java) 
    -  [`analyze-class-defs?`](#clj-kondo.impl.analysis.java/analyze-class-defs?)
    -  [`analyze-class-usages?`](#clj-kondo.impl.analysis.java/analyze-class-usages?)
    -  [`class->class-name`](#clj-kondo.impl.analysis.java/class->class-name)
    -  [`entry->class-name`](#clj-kondo.impl.analysis.java/entry->class-name)
    -  [`file->bytes`](#clj-kondo.impl.analysis.java/file->bytes)
    -  [`java-class-def-analysis?`](#clj-kondo.impl.analysis.java/java-class-def-analysis?)
    -  [`reg-class-def!`](#clj-kondo.impl.analysis.java/reg-class-def!)
    -  [`reg-class-usage!`](#clj-kondo.impl.analysis.java/reg-class-usage!)
    -  [`source->class-name`](#clj-kondo.impl.analysis.java/source->class-name)
-  [`clj-kondo.impl.analyzer.clojure-data-xml`](#clj-kondo.impl.analyzer.clojure-data-xml) 
    -  [`->alias-node`](#clj-kondo.impl.analyzer.clojure-data-xml/->alias-node)
    -  [`analyze-alias-uri`](#clj-kondo.impl.analyzer.clojure-data-xml/analyze-alias-uri)
    -  [`analyze-export-api`](#clj-kondo.impl.analyzer.clojure-data-xml/analyze-export-api)
    -  [`encode-uri`](#clj-kondo.impl.analyzer.clojure-data-xml/encode-uri)
    -  [`uri-symbol`](#clj-kondo.impl.analyzer.clojure-data-xml/uri-symbol)
-  [`clj-kondo.impl.analyzer.match`](#clj-kondo.impl.analyzer.match) 
    -  [`analyze-children`](#clj-kondo.impl.analyzer.match/analyze-children)
    -  [`analyze-expr`](#clj-kondo.impl.analyzer.match/analyze-expr)
    -  [`analyze-list`](#clj-kondo.impl.analyzer.match/analyze-list)
    -  [`analyze-match`](#clj-kondo.impl.analyzer.match/analyze-match)
    -  [`analyze-token`](#clj-kondo.impl.analyzer.match/analyze-token)
    -  [`analyze-vector`](#clj-kondo.impl.analyzer.match/analyze-vector)
    -  [`into*`](#clj-kondo.impl.analyzer.match/into*)
    -  [`reg-used-binding!`](#clj-kondo.impl.analyzer.match/reg-used-binding!)
-  [`clj-kondo.impl.docstring`](#clj-kondo.impl.docstring) 
    -  [`docs-from-meta`](#clj-kondo.impl.docstring/docs-from-meta) - Return a tuple of <code>[doc-node docstring]</code> given <code>meta-node</code>.
    -  [`lint-docstring!`](#clj-kondo.impl.docstring/lint-docstring!) - Lint <code>docstring</code> for styling issues.
    -  [`safe-char-at`](#clj-kondo.impl.docstring/safe-char-at)
-  [`clj-kondo.impl.linters.config`](#clj-kondo.impl.linters.config)  - Linting of clj-kondo's own configuration.
    -  [`expected-linter-keys`](#clj-kondo.impl.linters.config/expected-linter-keys)
    -  [`lint-config`](#clj-kondo.impl.linters.config/lint-config)
    -  [`lint-linters`](#clj-kondo.impl.linters.config/lint-linters)
    -  [`lint-map-vals`](#clj-kondo.impl.linters.config/lint-map-vals)
-  [`clj-kondo.impl.linters.deps-edn`](#clj-kondo.impl.linters.deps-edn)  - Linter for deps.edn and bb.edn file contents.
    -  [`derive-git-url-from-lib`](#clj-kondo.impl.linters.deps-edn/derive-git-url-from-lib)
    -  [`lint-alias-keys`](#clj-kondo.impl.linters.deps-edn/lint-alias-keys)
    -  [`lint-aliases`](#clj-kondo.impl.linters.deps-edn/lint-aliases)
    -  [`lint-bb-edn`](#clj-kondo.impl.linters.deps-edn/lint-bb-edn)
    -  [`lint-bb-edn-paths`](#clj-kondo.impl.linters.deps-edn/lint-bb-edn-paths)
    -  [`lint-bb-edn-paths-elems`](#clj-kondo.impl.linters.deps-edn/lint-bb-edn-paths-elems)
    -  [`lint-dep-coord`](#clj-kondo.impl.linters.deps-edn/lint-dep-coord)
    -  [`lint-deps`](#clj-kondo.impl.linters.deps-edn/lint-deps)
    -  [`lint-deps-edn`](#clj-kondo.impl.linters.deps-edn/lint-deps-edn)
    -  [`lint-deps-edn-paths`](#clj-kondo.impl.linters.deps-edn/lint-deps-edn-paths)
    -  [`lint-deps-edn-paths-elems`](#clj-kondo.impl.linters.deps-edn/lint-deps-edn-paths-elems)
    -  [`lint-mvn-repos`](#clj-kondo.impl.linters.deps-edn/lint-mvn-repos)
    -  [`lint-paths-container`](#clj-kondo.impl.linters.deps-edn/lint-paths-container)
    -  [`lint-qualified-deps`](#clj-kondo.impl.linters.deps-edn/lint-qualified-deps)
    -  [`lint-qualified-lib`](#clj-kondo.impl.linters.deps-edn/lint-qualified-lib)
    -  [`lint-tasks`](#clj-kondo.impl.linters.deps-edn/lint-tasks)
-  [`clj-kondo.impl.linters.edn-utils`](#clj-kondo.impl.linters.edn-utils) 
    -  [`key-nodes`](#clj-kondo.impl.linters.edn-utils/key-nodes)
    -  [`name-for-type`](#clj-kondo.impl.linters.edn-utils/name-for-type)
    -  [`node-map`](#clj-kondo.impl.linters.edn-utils/node-map)
    -  [`sexpr-keys`](#clj-kondo.impl.linters.edn-utils/sexpr-keys)
    -  [`val-nodes`](#clj-kondo.impl.linters.edn-utils/val-nodes)

-----
# <a name="aaaa-this-has-to-be-first.pprint">aaaa-this-has-to-be-first.pprint</a>






## <a name="aaaa-this-has-to-be-first.pprint/new-table-ize">`new-table-ize`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/aaaa_this_has_to_be_first/pprint.clj#L13-L18)
<a name="aaaa-this-has-to-be-first.pprint/new-table-ize"></a>
``` clojure

(new-table-ize t m)
```


## <a name="aaaa-this-has-to-be-first.pprint/new-write">`new-write`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/aaaa_this_has_to_be_first/pprint.clj#L28-L49)
<a name="aaaa-this-has-to-be-first.pprint/new-write"></a>
``` clojure

(new-write object & kw-args)
```


## <a name="aaaa-this-has-to-be-first.pprint/patched?">`patched?`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/aaaa_this_has_to_be_first/pprint.clj#L5-L5)
<a name="aaaa-this-has-to-be-first.pprint/patched?"></a>

-----
# <a name="clj-kondo.core">clj-kondo.core</a>






## <a name="clj-kondo.core/config-hash">`config-hash`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/core.clj#L260-L265)
<a name="clj-kondo.core/config-hash"></a>
``` clojure

(config-hash config)
```


Return the hash of the provided clj-kondo config.

## <a name="clj-kondo.core/merge-configs">`merge-configs`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/core.clj#L247-L250)
<a name="clj-kondo.core/merge-configs"></a>
``` clojure

(merge-configs & configs)
```


Returns the merged configuration of c1 with c2.

## <a name="clj-kondo.core/print!">`print!`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/core.clj#L19-L53)
<a name="clj-kondo.core/print!"></a>
``` clojure

(print! {:keys [:config :findings :summary :analysis]})
```


Prints the result from [`run!`](#clj-kondo.core/run!) to `*out*`. Returns `nil`. Alpha,
  subject to change.

## <a name="clj-kondo.core/resolve-config">`resolve-config`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/core.clj#L252-L258)
<a name="clj-kondo.core/resolve-config"></a>
``` clojure

(resolve-config cfg-dir)
(resolve-config cfg-dir config)
```


Returns the configuration for `cfg-dir` merged with home,
  clj-kondo default configs and optional `config` if provided.

## <a name="clj-kondo.core/run!">`run!`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/core.clj#L55-L245)
<a name="clj-kondo.core/run!"></a>
``` clojure

(run!
 {:keys
  [:lint
   :lang
   :filename
   :cache
   :cache-dir
   :config
   :config-dir
   :parallel
   :no-warnings
   :dependencies
   :copy-configs
   :custom-lint-fn
   :file-analyzed-fn
   :skip-lint
   :debug],
  :or {cache true}})
```


Takes a map with:

  - `:lint`: a seqable of files, directories and/or classpaths to lint.

  - `:lang`: optional, defaults to `:clj`. Sets language for linting
  `*in*`. Supported values: `:clj`, `:cljs` and `:cljc`.

  - `:filename`: optional. In case stdin is used for linting, use this
  to set the reported filename.

  - `:cache-dir`: when this option is provided, the cache will be
  resolved to this directory. If `:cache` is `false` this option will
  be ignored.

  - `:cache`: if `false`, won't use cache. Otherwise, will try to resolve cache
  using `:cache-dir`. If `:cache-dir` is not set, cache is resolved using the
  nearest `.clj-kondo` directory in the current and parent directories.

  - `:config`: optional. A seqable of maps, a map or string
  representing the config as EDN, or a config file.

  In places where a file-like value is expected, either a path as string or a
  `java.io.File` may be passed, except for a classpath which must always be a string.

  - `:parallel`: optional. A boolean indicating if sources should be linted in parallel.`

  - `:copy-configs`: optional. A boolean indicating if scanned hooks should be copied to clj-kondo config dir.`

  - `:skip-lint`: optional. A boolean indicating if linting should be
  skipped. Other tasks like copying configs will still be done if `:copy-configs` is true.`

  - `:debug`: optional. Print debug info.

  Returns a map with `:findings`, a seqable of finding maps, a
  `:summary` of the findings and the `:config` that was used to
  produce those findings. This map can be passed to [`print!`](#clj-kondo.core/print!) to print
  to `*out*`. Alpha, subject to change.
  

-----
# <a name="clj-kondo.hooks-api">clj-kondo.hooks-api</a>






## <a name="clj-kondo.hooks-api/*reload*">`*reload*`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/hooks_api.clj#L109-L109)
<a name="clj-kondo.hooks-api/*reload*"></a>

## <a name="clj-kondo.hooks-api/coerce">`coerce`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/hooks_api.clj#L68-L69)
<a name="clj-kondo.hooks-api/coerce"></a>
``` clojure

(coerce s-expr)
```


## <a name="clj-kondo.hooks-api/keyword-node">`keyword-node`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/hooks_api.clj#L22-L23)
<a name="clj-kondo.hooks-api/keyword-node"></a>

## <a name="clj-kondo.hooks-api/keyword-node?">`keyword-node?`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/hooks_api.clj#L19-L20)
<a name="clj-kondo.hooks-api/keyword-node?"></a>
``` clojure

(keyword-node? n)
```


## <a name="clj-kondo.hooks-api/list-node">`list-node`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/hooks_api.clj#L45-L45)
<a name="clj-kondo.hooks-api/list-node"></a>

## <a name="clj-kondo.hooks-api/list-node?">`list-node?`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/hooks_api.clj#L43-L43)
<a name="clj-kondo.hooks-api/list-node?"></a>

## <a name="clj-kondo.hooks-api/macroexpand">`macroexpand`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/hooks_api.clj#L151-L159)
<a name="clj-kondo.hooks-api/macroexpand"></a>
``` clojure

(macroexpand macro node bindings)
```


## <a name="clj-kondo.hooks-api/map-node">`map-node`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/hooks_api.clj#L51-L51)
<a name="clj-kondo.hooks-api/map-node"></a>

## <a name="clj-kondo.hooks-api/map-node?">`map-node?`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/hooks_api.clj#L47-L49)
<a name="clj-kondo.hooks-api/map-node?"></a>
``` clojure

(map-node? n)
```


## <a name="clj-kondo.hooks-api/ns-analysis">`ns-analysis`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/hooks_api.clj#L95-L107)
<a name="clj-kondo.hooks-api/ns-analysis"></a>
``` clojure

(ns-analysis ns-sym)
(ns-analysis ns-sym {:keys [lang]})
```


Return any cached analysis for the namespace identified by ns-sym.
  Returns a map keyed by language keyword with values being maps of var
  definitions keyed by defined symbol. The value for each symbol is a
  subset of the values provide by the top level :analysis option.

## <a name="clj-kondo.hooks-api/parse-string">`parse-string`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/hooks_api.clj#L16-L17)
<a name="clj-kondo.hooks-api/parse-string"></a>
``` clojure

(parse-string s)
```


## <a name="clj-kondo.hooks-api/pprint">`pprint`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/hooks_api.clj#L161-L163)
<a name="clj-kondo.hooks-api/pprint"></a>
``` clojure

(pprint & args)
```


## <a name="clj-kondo.hooks-api/prewalk">`prewalk`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/hooks_api.clj#L118-L120)
<a name="clj-kondo.hooks-api/prewalk"></a>
``` clojure

(prewalk f form)
```


## <a name="clj-kondo.hooks-api/reg-finding!">`reg-finding!`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/hooks_api.clj#L59-L62)
<a name="clj-kondo.hooks-api/reg-finding!"></a>
``` clojure

(reg-finding! m)
```


## <a name="clj-kondo.hooks-api/reg-keyword!">`reg-keyword!`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/hooks_api.clj#L64-L66)
<a name="clj-kondo.hooks-api/reg-keyword!"></a>
``` clojure

(reg-keyword! k reg-by)
```


## <a name="clj-kondo.hooks-api/sexpr">`sexpr`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/hooks_api.clj#L53-L54)
<a name="clj-kondo.hooks-api/sexpr"></a>
``` clojure

(sexpr expr)
```


## <a name="clj-kondo.hooks-api/string-node">`string-node`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/hooks_api.clj#L28-L29)
<a name="clj-kondo.hooks-api/string-node"></a>

## <a name="clj-kondo.hooks-api/string-node?">`string-node?`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/hooks_api.clj#L25-L26)
<a name="clj-kondo.hooks-api/string-node?"></a>
``` clojure

(string-node? n)
```


## <a name="clj-kondo.hooks-api/tag">`tag`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/hooks_api.clj#L56-L57)
<a name="clj-kondo.hooks-api/tag"></a>
``` clojure

(tag n)
```


## <a name="clj-kondo.hooks-api/token-node">`token-node`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/hooks_api.clj#L34-L35)
<a name="clj-kondo.hooks-api/token-node"></a>

## <a name="clj-kondo.hooks-api/token-node?">`token-node?`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/hooks_api.clj#L31-L32)
<a name="clj-kondo.hooks-api/token-node?"></a>
``` clojure

(token-node? n)
```


## <a name="clj-kondo.hooks-api/vector-node">`vector-node`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/hooks_api.clj#L41-L41)
<a name="clj-kondo.hooks-api/vector-node"></a>

## <a name="clj-kondo.hooks-api/vector-node?">`vector-node?`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/hooks_api.clj#L37-L39)
<a name="clj-kondo.hooks-api/vector-node?"></a>
``` clojure

(vector-node? n)
```


## <a name="clj-kondo.hooks-api/walk">`walk`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/hooks_api.clj#L111-L116)
<a name="clj-kondo.hooks-api/walk"></a>
``` clojure

(walk inner outer form)
```


-----
# <a name="clj-kondo.impl.analysis.java">clj-kondo.impl.analysis.java</a>






## <a name="clj-kondo.impl.analysis.java/analyze-class-defs?">`analyze-class-defs?`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/analysis/java.clj#L51-L52)
<a name="clj-kondo.impl.analysis.java/analyze-class-defs?"></a>
``` clojure

(analyze-class-defs? ctx)
```


## <a name="clj-kondo.impl.analysis.java/analyze-class-usages?">`analyze-class-usages?`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/analysis/java.clj#L71-L73)
<a name="clj-kondo.impl.analysis.java/analyze-class-usages?"></a>
``` clojure

(analyze-class-usages? ctx)
```


## <a name="clj-kondo.impl.analysis.java/class->class-name">`class->class-name`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/analysis/java.clj#L22-L33)
<a name="clj-kondo.impl.analysis.java/class->class-name"></a>
``` clojure

(class->class-name class-file)
```


## <a name="clj-kondo.impl.analysis.java/entry->class-name">`entry->class-name`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/analysis/java.clj#L15-L20)
<a name="clj-kondo.impl.analysis.java/entry->class-name"></a>
``` clojure

(entry->class-name entry)
```


## <a name="clj-kondo.impl.analysis.java/file->bytes">`file->bytes`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/analysis/java.clj#L9-L13)
<a name="clj-kondo.impl.analysis.java/file->bytes"></a>
``` clojure

(file->bytes file)
```


## <a name="clj-kondo.impl.analysis.java/java-class-def-analysis?">`java-class-def-analysis?`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/analysis/java.clj#L48-L49)
<a name="clj-kondo.impl.analysis.java/java-class-def-analysis?"></a>
``` clojure

(java-class-def-analysis? ctx)
```


## <a name="clj-kondo.impl.analysis.java/reg-class-def!">`reg-class-def!`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/analysis/java.clj#L54-L69)
<a name="clj-kondo.impl.analysis.java/reg-class-def!"></a>
``` clojure

(reg-class-def! ctx {:keys [jar entry file]})
```


## <a name="clj-kondo.impl.analysis.java/reg-class-usage!">`reg-class-usage!`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/analysis/java.clj#L75-L84)
<a name="clj-kondo.impl.analysis.java/reg-class-usage!"></a>
``` clojure

(reg-class-usage! ctx class-name loc+data)
```


## <a name="clj-kondo.impl.analysis.java/source->class-name">`source->class-name`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/analysis/java.clj#L35-L46)
<a name="clj-kondo.impl.analysis.java/source->class-name"></a>
``` clojure

(source->class-name file)
```


-----
# <a name="clj-kondo.impl.analyzer.clojure-data-xml">clj-kondo.impl.analyzer.clojure-data-xml</a>






## <a name="clj-kondo.impl.analyzer.clojure-data-xml/->alias-node">`->alias-node`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/analyzer/clojure_data_xml.clj#L7-L19)
<a name="clj-kondo.impl.analyzer.clojure-data-xml/->alias-node"></a>
``` clojure

(->alias-node alias-node)
```


## <a name="clj-kondo.impl.analyzer.clojure-data-xml/analyze-alias-uri">`analyze-alias-uri`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/analyzer/clojure_data_xml.clj#L27-L42)
<a name="clj-kondo.impl.analyzer.clojure-data-xml/analyze-alias-uri"></a>
``` clojure

(analyze-alias-uri ctx expr)
```


## <a name="clj-kondo.impl.analyzer.clojure-data-xml/analyze-export-api">`analyze-export-api`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/analyzer/clojure_data_xml.clj#L44-L55)
<a name="clj-kondo.impl.analyzer.clojure-data-xml/analyze-export-api"></a>
``` clojure

(analyze-export-api ctx node)
```


## <a name="clj-kondo.impl.analyzer.clojure-data-xml/encode-uri">`encode-uri`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/analyzer/clojure_data_xml.clj#L21-L22)
<a name="clj-kondo.impl.analyzer.clojure-data-xml/encode-uri"></a>
``` clojure

(encode-uri uri)
```


## <a name="clj-kondo.impl.analyzer.clojure-data-xml/uri-symbol">`uri-symbol`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/analyzer/clojure_data_xml.clj#L24-L25)
<a name="clj-kondo.impl.analyzer.clojure-data-xml/uri-symbol"></a>
``` clojure

(uri-symbol uri)
```


-----
# <a name="clj-kondo.impl.analyzer.match">clj-kondo.impl.analyzer.match</a>






## <a name="clj-kondo.impl.analyzer.match/analyze-children">`analyze-children`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/analyzer/match.clj#L27-L37)
<a name="clj-kondo.impl.analyzer.match/analyze-children"></a>
``` clojure

(analyze-children ctx expr)
```


## <a name="clj-kondo.impl.analyzer.match/analyze-expr">`analyze-expr`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/analyzer/match.clj#L72-L85)
<a name="clj-kondo.impl.analyzer.match/analyze-expr"></a>
``` clojure

(analyze-expr ctx expr)
```


## <a name="clj-kondo.impl.analyzer.match/analyze-list">`analyze-list`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/analyzer/match.clj#L39-L51)
<a name="clj-kondo.impl.analyzer.match/analyze-list"></a>
``` clojure

(analyze-list ctx expr)
```


## <a name="clj-kondo.impl.analyzer.match/analyze-match">`analyze-match`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/analyzer/match.clj#L87-L95)
<a name="clj-kondo.impl.analyzer.match/analyze-match"></a>
``` clojure

(analyze-match ctx expr)
```


## <a name="clj-kondo.impl.analyzer.match/analyze-token">`analyze-token`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/analyzer/match.clj#L17-L23)
<a name="clj-kondo.impl.analyzer.match/analyze-token"></a>
``` clojure

(analyze-token ctx expr)
```


## <a name="clj-kondo.impl.analyzer.match/analyze-vector">`analyze-vector`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/analyzer/match.clj#L53-L70)
<a name="clj-kondo.impl.analyzer.match/analyze-vector"></a>
``` clojure

(analyze-vector ctx expr)
```


## <a name="clj-kondo.impl.analyzer.match/into*">`into*`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/analyzer/match.clj#L11-L15)
<a name="clj-kondo.impl.analyzer.match/into*"></a>
``` clojure

(into* ctx existing-bindings new-bindings)
```


## <a name="clj-kondo.impl.analyzer.match/reg-used-binding!">`reg-used-binding!`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/analyzer/match.clj#L5-L9)
<a name="clj-kondo.impl.analyzer.match/reg-used-binding!"></a>
``` clojure

(reg-used-binding! {:keys [:base-lang :lang :namespaces :ns]} binding)
```


-----
# <a name="clj-kondo.impl.docstring">clj-kondo.impl.docstring</a>






## <a name="clj-kondo.impl.docstring/docs-from-meta">`docs-from-meta`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/docstring.clj#L46-L60)
<a name="clj-kondo.impl.docstring/docs-from-meta"></a>
``` clojure

(docs-from-meta meta-node)
```


Return a tuple of `[doc-node docstring]` given `meta-node`.

  If `meta-node` is not a map node or does not contain a :doc entry,
  return nil.

## <a name="clj-kondo.impl.docstring/lint-docstring!">`lint-docstring!`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/docstring.clj#L12-L44)
<a name="clj-kondo.impl.docstring/lint-docstring!"></a>
``` clojure

(lint-docstring! {:keys [filename config], :as ctx} node docstring)
```


Lint `docstring` for styling issues.

  `node` is the node reported when docstring has findings, so ideally
  it should be the text node for `docstring`.

## <a name="clj-kondo.impl.docstring/safe-char-at">`safe-char-at`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/docstring.clj#L8-L10)
<a name="clj-kondo.impl.docstring/safe-char-at"></a>
``` clojure

(safe-char-at s idx len)
```


-----
# <a name="clj-kondo.impl.linters.config">clj-kondo.impl.linters.config</a>


Linting of clj-kondo's own configuration




## <a name="clj-kondo.impl.linters.config/expected-linter-keys">`expected-linter-keys`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/linters/config.clj#L10-L10)
<a name="clj-kondo.impl.linters.config/expected-linter-keys"></a>

## <a name="clj-kondo.impl.linters.config/lint-config">`lint-config`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/linters/config.clj#L49-L63)
<a name="clj-kondo.impl.linters.config/lint-config"></a>
``` clojure

(lint-config ctx expr)
```


## <a name="clj-kondo.impl.linters.config/lint-linters">`lint-linters`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/linters/config.clj#L24-L47)
<a name="clj-kondo.impl.linters.config/lint-linters"></a>
``` clojure

(lint-linters ctx node-map config-map)
```


## <a name="clj-kondo.impl.linters.config/lint-map-vals">`lint-map-vals`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/linters/config.clj#L12-L22)
<a name="clj-kondo.impl.linters.config/lint-map-vals"></a>
``` clojure

(lint-map-vals ctx node-map ks)
```


-----
# <a name="clj-kondo.impl.linters.deps-edn">clj-kondo.impl.linters.deps-edn</a>


Linter for deps.edn and bb.edn file contents.




## <a name="clj-kondo.impl.linters.deps-edn/derive-git-url-from-lib">`derive-git-url-from-lib`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/linters/deps_edn.clj#L68-L75)
<a name="clj-kondo.impl.linters.deps-edn/derive-git-url-from-lib"></a>
``` clojure

(derive-git-url-from-lib lib)
```


## <a name="clj-kondo.impl.linters.deps-edn/lint-alias-keys">`lint-alias-keys`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/linters/deps_edn.clj#L141-L158)
<a name="clj-kondo.impl.linters.deps-edn/lint-alias-keys"></a>
``` clojure

(lint-alias-keys ctx nodes)
```


## <a name="clj-kondo.impl.linters.deps-edn/lint-aliases">`lint-aliases`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/linters/deps_edn.clj#L160-L172)
<a name="clj-kondo.impl.linters.deps-edn/lint-aliases"></a>
``` clojure

(lint-aliases ctx alias-nodes)
```


## <a name="clj-kondo.impl.linters.deps-edn/lint-bb-edn">`lint-bb-edn`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/linters/deps_edn.clj#L237-L254)
<a name="clj-kondo.impl.linters.deps-edn/lint-bb-edn"></a>
``` clojure

(lint-bb-edn ctx expr)
```


## <a name="clj-kondo.impl.linters.deps-edn/lint-bb-edn-paths">`lint-bb-edn-paths`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/linters/deps_edn.clj#L43-L45)
<a name="clj-kondo.impl.linters.deps-edn/lint-bb-edn-paths"></a>
``` clojure

(lint-bb-edn-paths ctx node)
```


## <a name="clj-kondo.impl.linters.deps-edn/lint-bb-edn-paths-elems">`lint-bb-edn-paths-elems`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/linters/deps_edn.clj#L21-L30)
<a name="clj-kondo.impl.linters.deps-edn/lint-bb-edn-paths-elems"></a>
``` clojure

(lint-bb-edn-paths-elems ctx node)
```


## <a name="clj-kondo.impl.linters.deps-edn/lint-dep-coord">`lint-dep-coord`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/linters/deps_edn.clj#L77-L134)
<a name="clj-kondo.impl.linters.deps-edn/lint-dep-coord"></a>
``` clojure

(lint-dep-coord ctx lib node)
```


## <a name="clj-kondo.impl.linters.deps-edn/lint-deps">`lint-deps`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/linters/deps_edn.clj#L136-L139)
<a name="clj-kondo.impl.linters.deps-edn/lint-deps"></a>
``` clojure

(lint-deps ctx kvs)
```


## <a name="clj-kondo.impl.linters.deps-edn/lint-deps-edn">`lint-deps-edn`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/linters/deps_edn.clj#L256-L280)
<a name="clj-kondo.impl.linters.deps-edn/lint-deps-edn"></a>
``` clojure

(lint-deps-edn ctx expr)
```


## <a name="clj-kondo.impl.linters.deps-edn/lint-deps-edn-paths">`lint-deps-edn-paths`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/linters/deps_edn.clj#L47-L49)
<a name="clj-kondo.impl.linters.deps-edn/lint-deps-edn-paths"></a>
``` clojure

(lint-deps-edn-paths ctx node)
```


## <a name="clj-kondo.impl.linters.deps-edn/lint-deps-edn-paths-elems">`lint-deps-edn-paths-elems`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/linters/deps_edn.clj#L32-L41)
<a name="clj-kondo.impl.linters.deps-edn/lint-deps-edn-paths-elems"></a>
``` clojure

(lint-deps-edn-paths-elems ctx node)
```


## <a name="clj-kondo.impl.linters.deps-edn/lint-mvn-repos">`lint-mvn-repos`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/linters/deps_edn.clj#L174-L186)
<a name="clj-kondo.impl.linters.deps-edn/lint-mvn-repos"></a>
``` clojure

(lint-mvn-repos ctx mvn-repos)
```


## <a name="clj-kondo.impl.linters.deps-edn/lint-paths-container">`lint-paths-container`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/linters/deps_edn.clj#L10-L19)
<a name="clj-kondo.impl.linters.deps-edn/lint-paths-container"></a>
``` clojure

(lint-paths-container ctx node)
```


## <a name="clj-kondo.impl.linters.deps-edn/lint-qualified-deps">`lint-qualified-deps`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/linters/deps_edn.clj#L65-L66)
<a name="clj-kondo.impl.linters.deps-edn/lint-qualified-deps"></a>
``` clojure

(lint-qualified-deps ctx nodes)
```


## <a name="clj-kondo.impl.linters.deps-edn/lint-qualified-lib">`lint-qualified-lib`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/linters/deps_edn.clj#L51-L63)
<a name="clj-kondo.impl.linters.deps-edn/lint-qualified-lib"></a>
``` clojure

(lint-qualified-lib ctx node)
```


## <a name="clj-kondo.impl.linters.deps-edn/lint-tasks">`lint-tasks`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/linters/deps_edn.clj#L200-L235)
<a name="clj-kondo.impl.linters.deps-edn/lint-tasks"></a>
``` clojure

(lint-tasks ctx expr)
```


-----
# <a name="clj-kondo.impl.linters.edn-utils">clj-kondo.impl.linters.edn-utils</a>






## <a name="clj-kondo.impl.linters.edn-utils/key-nodes">`key-nodes`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/linters/edn_utils.clj#L13-L19)
<a name="clj-kondo.impl.linters.edn-utils/key-nodes"></a>
``` clojure

(key-nodes map-node)
```


## <a name="clj-kondo.impl.linters.edn-utils/name-for-type">`name-for-type`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/linters/edn_utils.clj#L28-L41)
<a name="clj-kondo.impl.linters.edn-utils/name-for-type"></a>
``` clojure

(name-for-type form)
```


## <a name="clj-kondo.impl.linters.edn-utils/node-map">`node-map`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/linters/edn_utils.clj#L43-L45)
<a name="clj-kondo.impl.linters.edn-utils/node-map"></a>
``` clojure

(node-map raw-node)
```


## <a name="clj-kondo.impl.linters.edn-utils/sexpr-keys">`sexpr-keys`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/linters/edn_utils.clj#L6-L11)
<a name="clj-kondo.impl.linters.edn-utils/sexpr-keys"></a>
``` clojure

(sexpr-keys map-node)
```


## <a name="clj-kondo.impl.linters.edn-utils/val-nodes">`val-nodes`</a> [:page_facing_up:](https://github.com/clj-kondo/clj-kondo/blob/master/../clj-kondo/src/clj_kondo/impl/linters/edn_utils.clj#L21-L26)
<a name="clj-kondo.impl.linters.edn-utils/val-nodes"></a>
``` clojure

(val-nodes map-node)
```

