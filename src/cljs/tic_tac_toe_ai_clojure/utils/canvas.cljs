(ns tic-tac-toe-ai-clojure.utils.canvas)

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


(defn clear-canvas! [ctx width height]
  (.clearRect ctx 0 0 width height))

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

(defn draw-board-lines! [theme ctx w h]
  (let [lines (get-board-lines w h)]
    (doseq [line lines] (draw-line! theme ctx line))))

(defn draw-initial-game! [theme ctx raf game]
  ;; (draw-board-lines-animation! (get theme :board-lines) ctx raf)
  )

(defn draw-game! [theme ctx raf game w h]
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
  (if (not (get game :started)) ;; Remove not after draw-board-lines-animation
    (draw-game! theme ctx raf game width height)
    (draw-initial-game! theme ctx raf game))))
