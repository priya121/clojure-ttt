(ns clojure-ttt.game
(:require [clojure-ttt.board :refer :all])
(:require [clojure-ttt.console :refer :all])
(:require [clojure-ttt.computer :as computer]))

(def new-board ["-" "-" "-"
                "-" "-" "-"
                "-" "-" "-"])

(defn play-h-h [board x o]
  (prompt-user)
  (display board)
  (let [board (mark board (valid (digit-input)) (calculate-player board))]
    (if (or (= false (board-not-full board)) (= true (any-win? board)))
    (display board)
    (recur board x o))))

(defn switch-player-type [player]
  (if (= player :human) :computer :human))

(defn play-h-c [board x o]
  (prompt-user)
  (display board)
   (let [board (if (= x :human) (mark board (valid (digit-input)) (calculate-player board))
     (mark board (computer/move board) (calculate-player board))) current-player (switch-player-type x)]
    (if (or (= false (board-not-full board)) (= true (any-win? board)))
    (recur board current-player o))))

(defn play-c-h [board x o]
  (prompt-user)
  (display board)
   (let [board (if (= x :computer) (mark board (computer/move board) (calculate-player board))
     (mark board (valid (digit-input)) (calculate-player board))) current-player (switch-player-type x)]
    (if (or (= false (board-not-full board)) (= true (any-win? board)))
      (display board)
    (recur board current-player o))))

(defmulti start (fn [game-type] (:game game-type)))
(defmethod start :hvh [game-type]
  (display-hvh)
  (ask-board-size)
    (play-h-h (create-board) :human :human) )
(defmethod start :hvc [game-type]
  (display-hvc)
  (ask-board-size)
    (play-h-c (create-board) :human :computer))
(defmethod start :cvh [game-type]
  (display-cvh)
  (ask-board-size)
    (play-c-h (create-board) :computer :human))

(defn start-game []
  (display-menu)
  (let [input (digit-input)]
  (cond
  (= input 1) (start {:game :hvh})
  (= input 2) (start {:game :hvc})
  (= input 3) (start {:game :cvh})
    :else (start {:game :hvh}))))

