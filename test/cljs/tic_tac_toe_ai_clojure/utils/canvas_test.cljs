(ns tic-tac-toe-ai-clojure.utils.canvas-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [tic-tac-toe-ai-clojure.utils.canvas :as canvas]))

(deftest get-board-lines-90-90
  (testing "Get board lines board 90x90"
    (let [
      actual (canvas/get-board-lines 90 90)
      expected [[[30 0] [30 90]]
                [[60 0] [60 90]]
                [[0 30] [90 30]]
                [[0 60] [90 60]]]]
    (is (= actual expected)))))
