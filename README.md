# tic-tac-toe-ai-clojure

Rewriting [Tic Tac Toe Ai](https://tic-tac-toe-ai.surge.sh/) from JavaScript to ClojureScript.

## Development Mode

### Dependencies
* [Closure](https://clojure.org/)
* [ClosureScript](https://clojurescript.org/)
* [Leiningen](https://leiningen.org/)

### Run application:

```bash
  lein clean
  lein figwheel dev
```

Figwheel will automatically push cljs changes to the browser.

Wait a bit, then browse to [http://localhost:3449](http://localhost:3449).

### Run tests:

```bash
  lein clean
  lein doo phantom test once
```

The above command assumes that you have [phantomjs](https://www.npmjs.com/package/phantomjs) installed. However, please note that [doo](https://github.com/bensu/doo) can be configured to run cljs.test in many other JS environments (chrome, ie, safari, opera, slimer, node, rhino, or nashorn). 

### cljs-devtools

To enable:

1. Open Chrome's DevTools,`Ctrl-Shift-i`
2. Open "Settings", `F1`
3. Check "Enable custom formatters" under the "Console" section
4. close and re-open DevTools

### Start Cider from Emacs:

Put this in your Emacs config file:

```
(setq cider-cljs-lein-repl "(do (use 'figwheel-sidecar.repl-api) (start-figwheel!) (cljs-repl))")
```

Navigate to a clojurescript file and start a figwheel REPL with `cider-jack-in-clojurescript` or (`C-c M-J`)

### Compile css:

Compile css file once.

```bash
  lein garden once
```

Automatically recompile css file on change.

```bash
  lein garden auto
```

## Production Build

```bash
  lein clean
  lein cljsbuild once min
```

## Template

Link: [reagent-figwheel](https://github.com/gadfly361/reagent-figwheel)

```bash
  lein new reagent-figwheel tic-tac-toe-ai-clojure +garden +test +devtools +cider +keechma
```

* React
  * [reagent](https://github.com/reagent-project/reagent)
* CSS
	* [garden](https://github.com/noprompt/garden) (`+garden`)
* Development
	* [cljs.test](https://github.com/clojure/clojurescript/blob/master/src/main/cljs/cljs/test.cljs) and [doo](https://github.com/bensu/doo) (`+test`)
	* [cljs-devtools](https://github.com/binaryage/cljs-devtools) (`+devtools`)
* Editor
	* [cider](https://github.com/clojure-emacs/cider) (`+cider`)
* Frameworks
	* [keechma](https://github.com/keechma/keechma) (`+keechma`)
* Hot reloading
  * [lein-figwheel](https://github.com/bhauman/lein-figwheel)
