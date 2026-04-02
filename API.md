# Table of contents
-  [`multiline-backtick`](#multiline-backtick) 
    -  [`create-temp-dir`](#multiline-backtick/create-temp-dir) - Creates a temp dir.
    -  [`delete-tree`](#multiline-backtick/delete-tree) - Deletes a tree.
    -  [`with-temp-dir`](#multiline-backtick/with-temp-dir) - Evaluates body with binding-name bound to the result of <code>(create-temp-dir options)</code>, then cleans up.

-----
# <a name="multiline-backtick">multiline-backtick</a>






## <a name="multiline-backtick/create-temp-dir">`create-temp-dir`</a>
``` clojure
(create-temp-dir)
```
Function.

Creates a temp dir.
<p><sub><a href="/blob/main/test-resources/multiline_backtick.clj#L3-L5">Source</a></sub></p>

## <a name="multiline-backtick/delete-tree">`delete-tree`</a>
``` clojure
(delete-tree)
```
Function.

Deletes a tree.
<p><sub><a href="/blob/main/test-resources/multiline_backtick.clj#L7-L9">Source</a></sub></p>

## <a name="multiline-backtick/with-temp-dir">`with-temp-dir`</a>
``` clojure
(with-temp-dir)
```
Function.

Evaluates body with binding-name bound to the result of `(create-temp-dir
options)`, then cleans up. See [`create-temp-dir`](#multiline-backtick/create-temp-dir) for valid `options`.

The directory will be removed with [`delete-tree`](#multiline-backtick/delete-tree) on exit from the scope.
<p><sub><a href="/blob/main/test-resources/multiline_backtick.clj#L11-L16">Source</a></sub></p>
