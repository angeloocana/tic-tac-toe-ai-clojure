(ns tic-tac-toe-ai-clojure.utils.theme)

(def black "#1E2019")
(def white "#D3D0CB")

(def colors {
  :white white
  :black black
  :yellow "#E2C044"
  :red "#FF0000"
  :darkBlue "#587B7F"
  :blue "#85B9BF"
  :green "#9FE80C"
  :gray "#393E41"

  :black-shades (vector "#32342D")
})

(def color white)
(def background-color black)

(def theme {
  :colors colors,
  :background-color background-color
  :color color
})

(def theme-game {
  :positions {
    :x {
      :line-color (get colors :white)
      :line-width 4
      :percentage-by-frame 15
      :delay-after-each-line 10
    }
    :o {
      :line-color (get colors :white)
      :line-width 4
      :percentage-by-frame 5
    }
  }
  :board-lines {
    :line-color (get colors :white)
    :line-width 2
    :percentage-by-frame 10
    :delay-after-each-line 10
  }
  :winLine {
    :line-color (get colors :green)
    :line-width 8
    :percentage-by-frame 10
    :delay-after-each-line 10
  }
})
