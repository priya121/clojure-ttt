(ns clojure-ttt.game
(:require [clojure-ttt.board :refer :all])
(:require [clojure-ttt.console :refer :all])
(:require [clojure-ttt.computer :refer :all]))

(defn play [board size x o]
  (println (display board size))
  (let [board (mark-position board (valid-digit (user-position)) (calculate-player board))]
    (println (display board size))
    (if (or (= false (board-not-full board)) (= true (win? board size)))
    (display board size)
    (recur board size x o))))

(defn switch-player-type [player]
  (if (= player :human) :computer :human))

(defn play-h-c [board size x o]
  (println (display board size))
   (let [board (if (= x :human) (mark-position board (valid-digit (user-position)) (calculate-player board))
                   (mark-position board (move board size) (calculate-player board))) current-player (switch-player-type x)]
    (println (display board size))
    (if (or (= false (board-not-full board)) (= true (win? board size)))
    (display board size)
    (recur board size current-player o))))

(defn start-game [board size]
  (println "Enter 1 for HvH or 2 for HvC: ")
  (if (= (user-position) 1)
    (do (println (str "Human v Human Game\n Enter a position from 0 - 8\n"))
        (let [x :human
              o :human] (play board size x o)))
    (do (let [x :human
              o :computer]
      (println (str "Human v Computer Game\n Enter a position from 0 - 8\n"))
      (play-h-c board size x o)))))

(defn get-player-index [player]
  (if (= player :human) (valid-digit (user-position)) (computer-position 3)))
