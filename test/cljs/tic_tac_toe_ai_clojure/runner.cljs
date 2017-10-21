(ns tic-tac-toe-ai-clojure.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [tic-tac-toe-ai-clojure.core-test]))

(doo-tests 'tic-tac-toe-ai-clojure.core-test)
