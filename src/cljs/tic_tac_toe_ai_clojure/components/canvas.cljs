(ns tic-tac-toe-ai-clojure.components.canvas
  (:require
    [reagent.core :as reagent]))

(enable-console-print!)

(defn draw [canvas]
  (let [ctx (.getContext canvas "2d")
        w (.-clientWidth canvas)
        h (.-clientHeight canvas)]
    (println ">>> draw start")
    (.beginPath ctx)
    (.moveTo ctx 0 0)
    (.lineTo ctx w h)
    (.moveTo ctx w 0)
    (.lineTo ctx 0 h)
    (.stroke ctx)))

(defn component [game selectPosition]
  (let [dom-node (reagent/atom nil)]
    (reagent/create-class
      {
        :component-did-update
        (fn []
          (draw (.-firstChild @dom-node)))

        :component-did-mount
        (fn [this]
          (reset! dom-node (reagent/dom-node this))
          (draw (.-firstChild @dom-node)))

        :reagent-render
        (fn []          
          [:div
            [:canvas]])
      })))
