# Quickdoc

## API docs

See [API.md](API.md).

## Rationale

This library came out of the desire to have a fast and light weight tool that
produces API docs from any Clojure code (`.clj`, `.cljs`, `.cljc`) without
executing that code. This tool produces pure Markdown that you can read directly
on Github and the output does not need CSS or JavaScript.

Quickdoc's properties:

- Based on [clj-kondo static analysis](https://github.com/clj-kondo/clj-kondo/tree/master/analysis)
- Fast to run using [babashka](#babashka) (around 100ms for this project)

## Projects using quickdoc

- [fs](https://github.com/babashka/fs/blob/master/API.md)
- [process](https://github.com/babashka/process/blob/master/API.md)
- [quickdoc](https://github.com/borkdude/quickdoc/blob/main/API.md)
- [SCI](https://github.com/babashka/sci/blob/master/API.md)
- [muutos](https://tangled.org/@flowthing.tngl.sh/muutos/blob/main/docs/API.md)
- [rv](https://github.com/fogus/rv/blob/main/doc/API.md)

## Babashka

### task

Use as a babashka dependency and task:

``` clojure
# bb.edn
:tasks {
,,,
quickdoc {:doc "Invoke quickdoc"
          :extra-deps {io.github.borkdude/quickdoc {:git/tag "v0.2.5", :git/sha "25784ca"}}
          :task (exec 'quickdoc.api/quickdoc)
          :exec-args {:git/branch "master"
                      :github/repo "https://github.com/clj-kondo/clj-kondo"
                      :source-paths ["src/clj_kondo/core.clj"]}}
,,,
}
```

Now you can run `bb quickdoc` and your API docs will be generated in `API.md`.

## Clojure CLI

Add the following alias to your global or project-local `deps.edn`:

``` clojure
:aliases {
,,,
:quickdoc
{:deps {io.github.borkdude/quickdoc {:git/tag "v0.2.5" :git/sha "25784ca"}
 :main-opts ["-m" "babashka.cli.exec" "quickdoc.api" "quickdoc"]}
,,,
}
```

Then you can call quickdoc using:

``` clojure
clj -M:quickdoc :github/repo https://github.com/clj-kondo :git/branch master
```

You can add default arguments to `:exec-args` in the alias:

``` clojure
:quickdoc
{,,,
 :exec-args {:github/repo "https://github.com/clj-kondo"
             :git/branch "master"}
```

So the command line invocation simply becomes:

``` clojure
clj -M:quickdoc
```

## Clojure tool

Quickdoc is also available as a [clj
tool](https://clojure.org/reference/deps_and_cli#_tool_usage). Note that this
way of invoking quickdoc is slower to start than with babashka.

To install the latest version, run:

``` clojure
clj -Ttools install-latest :lib io.github.borkdude/quickdoc :as quickdoc
```

To install a specific version, run:

```
clj -Ttools install io.github.borkdude/quickdoc '{:git/tag "v0.2.5" :git/sha "25784ca"}' :as quickdoc
```


Then invoke quickdoc using:

```
clj -Tquickdoc quickdoc '{:github/repo "https://github.com/borkdude/quickdoc"}'
```

## Can it be improved to do ...?

Probably yes! Let me know in [Github Discussions](https://github.com/borkdude/quickdoc/discussions) or create an [issue](https://github.com/borkdude/quickdoc/issues).

## License

See [LICENSE](LICENSE).
