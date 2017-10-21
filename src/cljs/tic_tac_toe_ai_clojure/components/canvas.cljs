(ns tic-tac-toe-ai-clojure.components.canvas
  (:require [reagent.core :as reagent])
  (:use [tic-tac-toe-ai-clojure.utils.canvas :only [draw!]]))

(enable-console-print!)

(defn component [theme game selectPosition]
  (let [dom-node (reagent/atom nil)
        raf reagent/next-tick]
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
            [:canvas]])
      })))
