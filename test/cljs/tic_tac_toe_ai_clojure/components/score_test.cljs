(ns tic-tac-toe-ai-clojure.components.score-test
  (:require [cljs.test :refer-macros [deftest testing is]])
  (:use [tic-tac-toe-ai-clojure.components.score :only [component]]
        [tic-tac-toe-ai-clojure.game :only [get-initial-game]]))

(deftest render-initial-score
  "TODO: Test snapshot"
  (testing "Render initial score"
    (is (component (get-initial-game)))))
