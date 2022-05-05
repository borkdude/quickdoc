# Quickdoc

## API docs

See [API.md](API.md).

## Rationale

This library came out of the desire to have a fast and light weight tool that
produces API docs from any Clojure code (`.clj`, `.cljs`, `.cljc`) without
executing that code. This tool produces pure Markdown and the output does not
need CSS or JavaScript.

Quickdoc's properties:

- Based on [clj-kondo static analysis](https://github.com/clj-kondo/clj-kondo/tree/master/analysis)
- Fast to run using [babashka](#babashka) (around 100ms for this project)

## Projects using quickdoc

- [fs](https://github.com/babashka/fs/blob/master/API.md)
- [process](https://github.com/babashka/process/blob/master/API.md)
- [quickdoc](https://github.com/borkdude/quickdoc/blob/main/API.md)
- [SCI](https://github.com/babashka/sci/blob/master/API.md)

## Status

This project is just getting started. Therefore the API may still undergo
significant changes based on early usage and feedback.

## Babashka

Use as a babashka dependency and task:

``` clojure
{:pods {clj-kondo/clj-kondo {:version "2022.02.09"}}
 :deps {io.github.borkdude/quickdoc {:git/sha "<latest-sha>"}}

 :tasks
 {quickdoc {:doc "Invoke quickdoc"
            :requires ([quickdoc.api :as api])
            :task (api/quickdoc {:git/branch "main"
                                 :github/repo "https://github.com/babashka/process"})}}}
```

Now you can run `bb quickdoc` and your API docs will be generated in `API.md`.

## Clojure tool

Quickdoc is also available as a [clj
tool](https://clojure.org/reference/deps_and_cli#_tool_usage). Note that this
way of invoking quickdoc is slower than with babashka.

To install, run:

```
clj -Ttools install io.github.borkdude/quickdoc '{:deps/root "jvm" :git/sha "<latest-sha>"}' :as quickdoc
```

Then invoke quickdoc using:

```
clj -Tquickdoc quickdoc '{:github/repo "https://github.com/borkdude/quickdoc"}'
```

## Can it be improved to do ...?

Probably yes! Let me know in [Github Discussions](https://github.com/borkdude/quickdoc/discussions) or create an [issue](https://github.com/borkdude/quickdoc/issues).

## License

See [LICENSE](LICENSE).
