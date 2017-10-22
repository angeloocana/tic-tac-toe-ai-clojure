(ns tic-tac-toe-ai-clojure.utils.canvas)

(defn clear-canvas! [ctx width height]
  (.clearRect ctx 0 0 width height))

;; Animation

(defn create-frame [type data]
  "Create frame"
  {:type type
   :data data})

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

(defn get-line-mid-point [from to percentage]
  "Get line middle point by percentage"
  (let [per (/ percentage 100) ;; 10% => 0.1
        [from-x from-y] from
        [to-x to-y] to]
    [(+ from-x (* per (- to-x from-x)))
     (+ from-y (* per (- to-y from-y)))]))

(defn create-line-frame [data]
  "Create line frame"
  (create-frame "line" data))

;; Get a list of points [ [0 0] [0 10] [0 20] [0 30]]
;; returns a list of pairs [from to] [ [[0 0] [0 10]] [[0 10] [0 20]] [[0 20] [030]]]
(defn get-pairs-from-to
  ([points]
    (let [first-line [(first points) (second points)]]
      (reduce get-pairs-from-to [first-line] (next (next points)))))
  ([lines point]
    (let [from (second (last lines))
          line [from point]]
      (concat lines [line]))))

(defn get-line-frames [per from to]
  (let [percentages (get-percentages per)
        points (map (partial get-line-mid-point from to) percentages)
        frames (get-pairs-from-to points)]
    (map create-line-frame frames)))

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
