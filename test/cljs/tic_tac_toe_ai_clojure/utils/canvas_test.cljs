(ns tic-tac-toe-ai-clojure.utils.canvas-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [tic-tac-toe-ai-clojure.utils.canvas :as canvas]))

;; Animation
(deftest get-percentages-20
  (testing "Get percentages 20"
    (let [percentage 20
          expected-percentages [0 20 40 60 80 100]
          percentages (canvas/get-percentages percentage)]
          (is (= percentages expected-percentages)))))

(deftest get-nils-3
  (testing "Get nils 3"
    (let [n 3
          expected-nils [nil nil nil]
          nils (canvas/get-nils n)]
      (is (= nils expected-nils)))))

(deftest add-delay
  (testing "Add delay"
    (let [theme {:delay-after-each-line 2}
          lines [[[0 10] [0 20]]
                 [[10 0] [20 0]]]
          expected-frames [[[0 10] [0 20]]
                           [[10 0] [20 0]]
                           nil nil]
          frames (canvas/add-delay theme lines)]
      (is (= frames expected-frames)))))

;; Draw line

(deftest get-line-mid-point-0
  (testing "Get line mid point 0%"
    (let [from [0 0]
          to [0 100]
          per 0
          expected-mid-point [0 0]
          mid-point (canvas/get-line-mid-point from to per)]
      (is (= mid-point expected-mid-point)))))

(deftest get-line-mid-point-100
  (testing "Get line mid point 100%"
    (let [from [0 0]
          to [0 100]
          per 100
          expected-mid-point [0 100]
          mid-point (canvas/get-line-mid-point from to per)]
      (is (= mid-point expected-mid-point)))))

(deftest get-line-frames-0-0-0-100-10
  (testing "Get line frames"
    (let [from [0 0]
          to [0 100]
          line [from to]
          theme {:percentage-by-frame 10}
          expected-frames [
            { :type "line" :data [[0  0] [0  10]] }
            { :type "line" :data [[0 10] [0  20]] }
            { :type "line" :data [[0 20] [0  30]] }
            { :type "line" :data [[0 30] [0  40]] }
            { :type "line" :data [[0 40] [0  50]] }
            { :type "line" :data [[0 50] [0  60]] }
            { :type "line" :data [[0 60] [0  70]] }
            { :type "line" :data [[0 70] [0  80]] }
            { :type "line" :data [[0 80] [0  90]] }
            { :type "line" :data [[0 90] [0 100]] }
          ]
          frames (canvas/get-line-frames theme line)]
      (is (= frames expected-frames)))))

(deftest get-lines-frames
  (testing "Get lines frames"
    (let [theme {:percentage-by-frame 25
                 :delay-after-each-line 5}
          lines [[[0 0][0 100]]
                 [[0 0][100 0]]]
          expected-frames [{:type "line", :data [[0  0] [0  25]]}
                           {:type "line", :data [[0 25] [0  50]]} 
                           {:type "line", :data [[0 50] [0  75]]} 
                           {:type "line", :data [[0 75] [0 100]]} 
                           nil nil nil nil nil
                           {:type "line", :data [[0  0] [25  0]]} 
                           {:type "line", :data [[25 0] [50  0]]} 
                           {:type "line", :data [[50 0] [75  0]]} 
                           {:type "line", :data [[75 0] [100 0]]} 
                           nil nil nil nil nil]
          frames (canvas/get-lines-frames theme lines)]
      (is (= frames expected-frames)))))

;; Draw board

(deftest get-board-lines-90-90
  (testing "Get board lines board 90x90"
    (let [
      lines (canvas/get-board-lines 90 90)
      expected-lines [[[30 0] [30 90]]
                [[60 0] [60 90]]
                [[0 30] [90 30]]
                [[0 60] [90 60]]]]
    (is (= lines expected-lines)))))


;; Draw game
