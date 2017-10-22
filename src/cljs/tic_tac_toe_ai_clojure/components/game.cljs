(ns tic-tac-toe-ai-clojure.components.game  
  (:require    
    [keechma.ui-component :as ui]
    [tic-tac-toe-ai-clojure.components.score :as score]
    [tic-tac-toe-ai-clojure.components.canvas :as canvas])
  (:use 
    [tic-tac-toe-ai-clojure.game :only [get-initial-game]]
    [tic-tac-toe-ai-clojure.utils.theme :only [background-color color]]))

(defn render []
  (let [game (get-initial-game)
        selectPosition (fn [])]
  [:section {:style {:background-color background-color
                     :color color
                     :position "fixed"
                     :top 0
                     :left 0
                     :right 0
                     :bottom 0
                     :text-align "center"}}
    [:h1 "Tic Tac Toe AI"]
    [score/component game]
    [canvas/component game selectPosition]
    [:div
      [:button "new-game"]]]))

(def component
  (ui/constructor
    {:renderer render
     :subscription-deps [:counter-value]}))

(def componentOld
  (ui/constructor
    {:renderer           (fn [] [:div [:h1 "Teste 1"]])
    :subscription-deps [:counter-value]}))
