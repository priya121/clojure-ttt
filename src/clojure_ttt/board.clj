(ns clojure-ttt.board
 (:require [clojure-ttt.computer :refer :all])
 (:require [clojure.math.numeric-tower :as math])
 (:require [clojure-ttt.console :refer :all]))

(defn create-board []
  (let [size (digit-input)]
  (vec (take (* size size) (repeat "-")))))

(defn empty-position [board position]
  (if (= "-" (get board position)) position
    (do (println "Please choose an empty square: \n") ( recur board (digit-input)))))

(defn mark [board position player]
  (assoc board (empty-position board position) player))

(defn divide-board [board dimension]
  (apply concat(interpose ["\n"] (partition dimension board))))

(defn display[board]
  (println (apply str (divide-board board (math/sqrt (count board))))))

(defn board-not-full[board]
  (contains? (set board) "-"))

(defn calculate-player [board]
  (if (#(= 0 (rem % 2)) (count (filter #(= "-" % ) board))) "O" "X"))

(defn get-indices [board]
  (map-indexed vector board))

(defn row-indices [size]
  (map #(into [] (range % (+ % size))) (range 0 (* size size) size)))

(defn column-indices [size]
  (map #(into [] ( range % (* size size) size)) (range 0 size)))

(defn diagonal-indices [size]
  [(take size (range (- size 1) (* size size) (- size 1)))
  (range 0 (* size size) (+ size 1))])

(defn winning-combo [board size]
  (concat (row-indices size) (column-indices size) (diagonal-indices size)))

(defn positions [mark board]
  (into #{} (map first (filter #(= (second %) mark) (get-indices board)))))

(defn win? [mark board]
  (if (some true? (map #(every? (positions mark board) %) (winning-combo board (math/sqrt (count board))))) (do (display-winner mark) true) false))

(defn any-win? [board]
  (if (or (win? "O" board) (win? "X" board)) true false))

