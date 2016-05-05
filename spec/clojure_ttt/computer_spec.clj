(ns clojure-ttt.computer_spec
  (:require [speclj.core :refer :all]
            [clojure.set :as set]
            [clojure-ttt.computer :refer :all]))

(def board ["X" "-" "-" "-" "-" "-" "-" "-"])
(def board-with-o ["O" "-" "-" "-" "-" "-" "-" "-"])

(describe "computer player"
  (it "returns a random number as an position to play"
  (should (= true (if (some #{(computer-position 3)} (range 0 9)) true false))))

  (it "only plays an empty position"
    (should (= true (not (= 0 (move board 3))))))

  (it "does not overwrite a player"
    (should (= true (not (= 0 (move board-with-o 3)))))))
