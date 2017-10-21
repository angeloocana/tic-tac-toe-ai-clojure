(ns tic-tac-toe-ai-clojure.game-test
  (:require [cljs.test :refer-macros [deftest testing is]])
  (:use [tic-tac-toe-ai-clojure.game :only [get-initial-game initial-game]]))

(deftest test-get-initial-game
  (testing "Get initial game"
    (let [game (get-initial-game)
          expected initial-game]
    (is (= game expected)))))

(deftest test-get-initial-game-2
  (testing "Get second game"
    (let [game (get-initial-game (get-initial-game))]
    (is (not (get game :is-ai-turn)))
    (is (not (get game :ai-started))))))
