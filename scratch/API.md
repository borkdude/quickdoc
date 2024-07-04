# Table of contents
-  [`user`](#user) 
    -  [`+and`](#user/+and) - Logical AND of heavy-bools which evaluates to a <code>heavy-bool</code>.
    -  [`heavy-bool`](#user/heavy-bool)

-----
# <a name="user">user</a>






## <a name="user/+and">`+and`</a><a name="user/+and"></a>
``` clojure

(+and & rest)
```
Macro.

Logical AND of heavy-bools which evaluates to a [`heavy-bool`](#user/heavy-bool).
  Expands to code which evaluates to the left-most [`heavy-bool`](#user/heavy-bool) value
  in the argument list, otherwise evaluates to the right-most
  value.  If the argument list is empty, evaluates explicitly to
  `+true`
<p><sub><a href="/blob/main/src/scratch.clj#L3-L18">Source</a></sub></p>

## <a name="user/heavy-bool">`heavy-bool`</a><a name="user/heavy-bool"></a>
``` clojure

(heavy-bool)
```
Function.
<p><sub><a href="/blob/main/src/scratch.clj#L1-L1">Source</a></sub></p>
