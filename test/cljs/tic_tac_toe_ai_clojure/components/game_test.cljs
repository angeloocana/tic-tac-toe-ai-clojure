(ns tic-tac-toe-ai-clojure.components.game-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [tic-tac-toe-ai-clojure.components.game :as game-component]
            [tic-tac-toe-ai-clojure.game :as game]))

(deftest render-initial-game
  (testing "Render initial game"
    (let [initial-game (game/get-initial-game)]
    (is (game-component/render initial-game)))))
