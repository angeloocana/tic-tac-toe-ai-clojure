(ns tic-tac-toe-ai-clojure.components.game  
  (:require    
    [keechma.ui-component :as ui]
    [tic-tac-toe-ai-clojure.components.score :as score])
  (:use 
    [tic-tac-toe-ai-clojure.game :only [get-initial-game]]))

(defn render []
  (let [game (get-initial-game)]
  [:section
    [:h1 "Tic Tac Toe AI"]
    [score/component game]]))

(def component
  (ui/constructor
    {:renderer render
     :subscription-deps [:counter-value]}))

(def componentOld
  (ui/constructor
    {:renderer           (fn [] [:div [:h1 "Teste 1"]])
    :subscription-deps [:counter-value]}))