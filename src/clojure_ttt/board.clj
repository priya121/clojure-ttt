(ns clojure-ttt.board
  (:require [clojure.set :as group]))

(defn mark [board position player]
  (assoc board position player))

(defn display[board dimension]
  (apply str(apply concat(interpose ["\n"] (partition dimension board)))))

(defn board-not-full[board]
  (contains? (set board) "-"))

(defn calculate-player [board]
  (if (#(= 0 (rem % 2)) (count (filter #(= "-" % ) board))) "O" "X"))

(defn get-indices [board]
  (map-indexed vector board))

(defn row-indices [board size]
  (doall (map #(range % (+ % size)) (for [[x y] (get-indices board) :when (= 0  (rem x size))] x))))

(defn column-indices [size]
  (doall (map #(range % (* size size) size) (range 0 size))))

(defn diagonal-indices [size]
  [(take size (range (- size 1) (* size size) (- size 1)))
   (range 0 (* size size) (+ size 1))])

(defn winning-combo [board size]
  (partition size (flatten [(row-indices board size) (column-indices size) (diagonal-indices size)])))

(defn positions [mark board]
  (map first (filter #(= (second %) mark) (get-indices board))))

(defn display-winner [player-mark]
  (println (str player-mark " is the winner!")))

(defn win-x? [board size]
  (if (#(= true %) (first (for [[x y z] (winning-combo board size) :when (= true (group/subset? #{x y z} (set(positions "X" board))))] true))) (do (display-winner "X") true) false))


(defn win-o? [board size]
  (if (#(= true %) (first (for [[x y z] (winning-combo board size) :when (= true (group/subset? #{x y z} (set(positions "O" board))))] true))) (do (display-winner "O") true) false))

(defn win? [board size]
  (if (or (win-o? board size) (win-x? board size)) true false))

