(ns clojure-ttt.computer
  (:require [clojure.math.numeric-tower :as math]))

(defn computer-position [size]
  (rand-int (* size size)))

(defn move [board]
  (let [temp-index (computer-position (math/sqrt (count board)))]
  (if (= "-" (get board temp-index (math/sqrt (count board)))) temp-index
    (move board))))

