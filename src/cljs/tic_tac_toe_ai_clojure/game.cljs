(ns tic-tac-toe-ai-clojure.game)

(def initial-game {
  :board 0
  :ended false
  :started false
  :lastMove nil
  :winners nil
  :isAiTurn true
  :aiStarted true
  :score {
    :ai 0,
    :human 0
  }
})

(defn get-next-game [old-game]
  "TODO: Implement changes"
  old-game)

(defn get-initial-game
  ([] initial-game)
  ([old-game] (get-next-game old-game)))
