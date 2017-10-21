(ns tic-tac-toe-ai-clojure.components.game-test
  (:require [cljs.test :refer-macros [deftest testing is]])
  (:use [tic-tac-toe-ai-clojure.game :only [get-initial-game]]
        [tic-tac-toe-ai-clojure.components.game :only [render]]))

(deftest render-initial-game
  "TODO: Test snapshot"
  (testing "Render initial game"
    (let [game (get-initial-game)]
    (is (render game)))))

(deftest render-second-game
  "TODO: Test snapshot"
  (testing "Render second game"
    (let [game (get-initial-game (get-initial-game))]
    (is (render game)))))
    