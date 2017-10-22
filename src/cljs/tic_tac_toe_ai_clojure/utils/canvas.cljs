(ns tic-tac-toe-ai-clojure.utils.canvas)

(def get-board-lines  
  (memoize
    (fn [w h]
      (let [sw (/ w 3)
            sw2 (* sw 2)]
        [ ;; col 1
          [[sw 0] [sw h]]
          ;; col 2
          [[sw2 0] [sw2 h]]
          ;; row 1
          [[0 sw] [h sw]]
          ;; row 2
          [[0 sw2] [h sw2]] 
        ]))))

(defn clear-canvas! [ctx width height]
  (.clearRect ctx 0 0 width height))

(defn draw-board-lines! [theme ctx]
  )

(defn draw-initial-game! [theme ctx raf game]
  ;; (draw-board-lines-animation! (get theme :board-lines) ctx raf)
  )

(defn draw-game! [theme ctx raf game]
  (let [{:keys [board-lines positions win-line]} theme]
    (draw-board-lines! board-lines ctx)
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
    (draw-game! theme ctx raf game)
    (draw-initial-game! theme ctx raf game))))
