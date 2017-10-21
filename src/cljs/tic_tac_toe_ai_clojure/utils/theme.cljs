(ns tic-tac-toe-ai-clojure.utils.theme)

(def colors {
  :white "#D3D0CB"
  :black "#1E2019"
  :yellow "#E2C044"
  :red "#FF0000"
  :darkBlue "#587B7F"
  :blue "#85B9BF"
  :green "#9FE80C"
  :gray "#393E41"

  :black-shades (vector "#32342D")
})


(def theme {
  :colors colors
})

(def theme-game {
  :positions {
    :x {
      :lineColor (get colors :white)
      :lineWidth 4
      :percentageByFrame 15
      :delayAfterEachLine 10
    }
    :o {
      :lineColor (get colors :white)
      :lineWidth 4
      :percentageByFrame 5
    }
  }
  :boardLines {
    :lineColor (get colors :white)
    :lineWidth 2
    :percentageByFrame 10
    :delayAfterEachLine 10
  }
  :winLine {
    :lineColor (get colors :green)
    :lineWidth 8
    :percentageByFrame 10
    :delayAfterEachLine 10
  }
})
