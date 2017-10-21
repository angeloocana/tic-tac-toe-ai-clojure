(ns tic-tac-toe-ai-clojure.utils.canvas)

(defn clear-canvas! [ctx width height]
  (.clearRect ctx 0 0 width height))

(defn draw-game! [theme canvas raf]
  (let [ctx (.getContext canvas "2d")
        width (.-clientWidth canvas)
        height (.-clientHeight canvas)]
  (clear-canvas! ctx width height)))