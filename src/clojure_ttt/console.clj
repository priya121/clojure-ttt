(ns clojure-ttt.console
(:require [clojure.set :as set]))

(defn take-input []
  (read-line))

(defn digit-input []
  (read-string (take-input)))

(defn show-initial-prompt []
  (println "Enter a position from 0 - 8: "))

(defn prompt-valid-digit []
  (println "Enter a valid number: "))

(defn display-winner [player-mark]
  (println (str player-mark " is the winner!")))

(defn display-menu []
  (println "Enter:\n1 for HvH\n2 for HvC\n3 for CvH\n"))

(defn display-hvh []
  (println (str "Human v Human Game\n")))

(defn display-hvc []
  (println (str "Human v Computer Game\n")))

(defn display-cvh []
  (println (str "Computer v Human Game\n")))

(defn ask-board-size []
  (println "Please enter a board size:\n(3 for 3x3 / 4 for 4x4 etc.)"))

(defn prompt-user []
  (println "Enter a position \n"))

(defn subset? [input]
  (set/subset? #{input} (set(range 0 9))))

(defn valid [input]
  (if (= true (subset? input)) input (do (prompt-valid-digit) (valid (digit-input)))))

