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
  (let [{:keys [ai-started score]} old-game]
  (merge old-game {
    :is-ai-turn (not ai-started)
    :aiStarted (not ai-started)
    score score})))

(defn get-initial-game
  ([] initial-game)
  ([old-game] (get-next-game old-game)))
