(ns tic-tac-toe-ai-clojure.components.game
  (:require
   [keechma.ui-component :as ui]
   ))

(defn render [] 
  [:div 
    [:h1 "Tic Tac Toe AI"]])

(def component
  (ui/constructor
    {:renderer render
    :subscription-deps [:counter-value]}))
