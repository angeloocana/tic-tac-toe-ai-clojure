(ns tic-tac-toe-ai-clojure.components.canvas
  (:require [reagent.core :as reagent])
  (:use [tic-tac-toe-ai-clojure.utils.canvas :only [draw-game!]]))

(enable-console-print!)

(defn component [game selectPosition]
  (let [dom-node (reagent/atom nil)]
    (reagent/create-class
      {
        :component-did-update
        (fn []
          (draw-game! theme (.-firstChild @dom-node) raf))

        :component-did-mount
        (fn [this]
          (reset! dom-node (reagent/dom-node this))
          (draw-game! theme (.-firstChild @dom-node) raf))

        :reagent-render
        (fn []          
          [:div
            [:canvas]])
      })))
