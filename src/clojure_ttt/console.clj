(ns clojure-ttt.console
(:require [clojure.set :as group]))

(defn take-input []
  (read-line))

(defn user-position []
  (read-string (take-input)))

(defn show-initial-prompt []
  (println "Enter a position from 0 - 8: "))

(defn valid-digit [input]
  (if (= true (group/subset? #{input} (set(range 0 8)))) input (do (println "Enter a valid number: ") (valid-digit (user-position)))))
