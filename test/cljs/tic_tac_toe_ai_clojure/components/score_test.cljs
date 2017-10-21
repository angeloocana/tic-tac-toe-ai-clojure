(ns tic-tac-toe-ai-clojure.components.score-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [tic-tac-toe-ai-clojure.components.score :as score]
            [tic-tac-toe-ai-clojure.game :as game]))

(deftest render-initial-game
  (testing "Render initial game"
    (let [
      initial-game (game/get-initial-game)
      expected [:div 
        [nil] 
        [:i "You"] 
        [:span 0] 
        [:span "x"] 
        [:span 0] 
        [:i "AI"] 
        [nil]]]
    (is (= (score/component initial-game) expected)))))
