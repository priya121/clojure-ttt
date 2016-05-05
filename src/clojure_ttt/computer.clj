(ns clojure-ttt.computer)

(defn mark []
  (str "O"))

(defn computer-position [size]
  (rand-int (* size size)))

(defn move [board size]
  (let [temp-index (computer-position size)]
  (if (= (or "-" "X" "O") (get board temp-index size)) temp-index
    (move board size))))

