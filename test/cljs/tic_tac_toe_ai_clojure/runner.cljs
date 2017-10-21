(ns tic-tac-toe-ai-clojure.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [tic-tac-toe-ai-clojure.core-test]
              [tic-tac-toe-ai-clojure.components.score-test]
              [tic-tac-toe-ai-clojure.components.game-test]))

(doo-tests 'tic-tac-toe-ai-clojure.core-test
           'tic-tac-toe-ai-clojure.game
           'tic-tac-toe-ai-clojure.components.score-test
           'tic-tac-toe-ai-clojure.components.game-test)
