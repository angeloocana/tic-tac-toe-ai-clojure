(ns tic-tac-toe-ai-clojure.components.score)

(defn get-end-msg [game forAi]
  (let [{:keys [winners isAiTurn]} game] 
    (if (and winners (= isAiTurn forAi)) "iLost" "iWon")))

(defn get-msg [game forAi]
  (if (get game :ended) (get-end-msg game forAi)))

(defn component [game]
  [:div
    [get-msg game false]
    [:i "You"]
    [:span (get-in game [:score :human])]
    [:span "x"]
    [:span (get-in game [:score :ai])]
    [:i "AI"]
    [get-msg game true]])
