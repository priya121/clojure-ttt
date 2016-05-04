(ns clojure-ttt.game
(:require [clojure-ttt.board :refer :all])
(:require [clojure-ttt.console :refer :all]))

(defn play [board size]
   (let [board (mark board (valid-digit (user-position)) (calculate-player board))]
    (println (display board size))
    (if (or (= false (board-not-full board)) (= true (win? board size)))
    (display board size)
    (recur board size))))



