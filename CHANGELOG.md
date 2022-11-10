# Changelog

Quickdoc

Quick and minimal API doc generation for Clojure

## v0.2.3

- Add `:filename-add-prefix` and `:filename-remove-prefix` options so quickdoc can more easily be configured from EDN

## v0.2.2

- Improved table of contents and source links ([@helins](https://github.com/helins))
- Support wiki syntax for var linking: `[[foo]]` ([@helins](https://github.com/helins))
- Upgrade clj-kondo to 2022.10.05

## v0.1.1

- [#14](https://github.com/borkdude/quickdoc/issues/14): Skip vars defined in comments
- [#18](https://github.com/borkdude/quickdoc/issues/18): Quickdoc ignores arglists metadata
- [#17](https://github.com/borkdude/quickdoc/issues/17): Change toc var descriptions to be the first sentence of their doc
