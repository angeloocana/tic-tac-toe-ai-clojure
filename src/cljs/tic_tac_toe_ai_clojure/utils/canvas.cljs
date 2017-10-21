(ns tic-tac-toe-ai-clojure.utils.canvas)

(defn clear-canvas! [ctx width height]
  (.clearRect ctx 0 0 width height))

(defn draw-initial-game! [theme ctx raf game]
  ;; (draw-board-lines-animation! (get theme :board-lines) ctx raf)
  )

(defn draw-game! [theme ctx raf game]
  (let [{:keys [board-lines positions win-line]} theme]
    ;; (draw-board-lines! boardLines ctx)
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
    (draw-game! theme ctx raf game)
    (draw-initial-game! theme ctx raf game))))
