(ns tic-tac-toe-ai-clojure.components.canvas
  (:require [reagent.core :as reagent])
  (:use [tic-tac-toe-ai-clojure.utils.canvas :only [draw!]]
        [tic-tac-toe-ai-clojure.utils.theme :only [theme-game]]))

(enable-console-print!)

(defn component [game selectPosition]
  (let [dom-node (reagent/atom nil)
        raf reagent/next-tick
        theme theme-game]
    (reagent/create-class
      {
        :component-did-update
        (fn []
          (draw! theme (.-firstChild @dom-node) raf game))

        :component-did-mount
        (fn [this]
          (reset! dom-node (reagent/dom-node this))
          (draw! theme (.-firstChild @dom-node) raf game))

        :reagent-render
        (fn []          
          [:div
            [:canvas {:width 200 :height 200}]])
      })))
