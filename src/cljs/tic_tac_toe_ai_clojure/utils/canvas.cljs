(ns tic-tac-toe-ai-clojure.utils.canvas)

(defn clear-canvas! [ctx width height]
  (.clearRect ctx 0 0 width height))

;; Animation

(defn add-frame [type frames data]
  "Concat a new frame to frames"
  (let [frame {:type type
               :data data}]
    (concat frames [frame])))

;; "Example per 20% => [0 20 40 60 80 100]"
(def get-percentages 
  (memoize (fn
    ([per] (get-percentages per [0]))
    ([per percentages]
      (let [last-per (last percentages)
            new-per (+ per last-per)]
        (if (< new-per 100) 
          (recur per (concat percentages [new-per]))
          (concat percentages [100])))))))

;; Draw line

(defn get-line-mid-point [from to per]
  "Get line middle point by percentage 0.1 === 10%"
  (let [[from-x from-y] from
        [to-x to-y] to]
    [(+ from-x (* per (- to-x from-x)))
     (+ from-y (* per (- to-y from-y)))]))

(defn add-line-frame [frames line]
  "Concat a new line frame to frames"
  (add-frame "line" frames line))

(defn get-line-frames [per from to]
  ; ([per from to] (get-line-frames per from to per []))
  ; ([per from to i frames]
  ;   "Split line into small lines to draw by frame"
  ;   (let [mid-line [(first (last frames))
  ;                   (get-line-mid-point from to (/ i 100))]]
  ;     (if (< per 100)
  ;       (recur per from to (+ i per) (add-line-frame frames mid-line)) 
  ;       frames))
        )

(defn get-lines-frames [theme lines]
  (map (get-line-frame (get theme :percentage-by-frame))))

(defn draw-line! [theme ctx line]
  "Draw a line to the canvas"
  (let [[from to] line]
    (let [{:keys [line-color line-width]} theme]
      (set! (. ctx -strokeStyle) line-color)
      (set! (. ctx -lineWidth) line-width))
    (.beginPath ctx)
    (let [[x y] from]
      (.moveTo ctx x y))
    (let [[x y] to]
      (.lineTo ctx x y))
    (.stroke ctx)))

(defn draw-lines-animation! [theme ctx raf lines]
  (let [frames (get-lines-frames theme lines)]
    (draw-animation! theme ctx raf frames)))
    
;; Draw board

(def get-board-lines  
  (memoize
    (fn [w h]
      "Get board lines [[[fromX fromY] [toX toY]]]
        w width
        h height
        sw square-width"
      (let [sw (/ w 3)
            sw2 (* sw 2)]
        [[[sw  0] [sw  h]] ;; col 1
         [[sw2 0] [sw2 h]] ;; col 2
         [[0  sw] [h  sw]] ;; row 1
         [[0 sw2] [h  sw2]]])))) ;; row 2

(defn draw-board-lines! [theme ctx w h]
  (let [lines (get-board-lines w h)]
    (doseq [line lines] (draw-line! theme ctx line))))

(defn draw-board-lines-animation! [theme ctx raf w h]
  (let [lines (get-board-lines w h)]
    (draw-lines-animation! theme ctx raf lines)))

;; Draw game

(defn draw-initial-game! [theme ctx raf game w h]
  "Draw initial game with animation"
  (draw-board-lines-animation! (get theme :board-lines) ctx raf w h))

(defn draw-game! [theme ctx raf game w h]
  "Draw game"
  (let [{:keys [board-lines positions win-line]} theme]
    (draw-board-lines! board-lines ctx w h)
    ;; (draw-positions! positions ctx game)  
    ;; (draw-last-move! positions ctx raf game)  
    ;; (draw-win-line! winLine ctx raf game)
    ))

(defn draw! [theme canvas raf game]
  (let [ctx (.getContext canvas "2d")
        width (.-clientWidth canvas)
        height (.-clientHeight canvas)]
  (clear-canvas! ctx width height)
  (if (get game :started)
    (draw-game! theme ctx raf game width height)
    (draw-initial-game! theme ctx raf game))))
