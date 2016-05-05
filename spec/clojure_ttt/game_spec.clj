(ns clojure-ttt.game_spec
  (:require [speclj.core :refer :all]
   [clojure-ttt.game :refer :all]
   [clojure-ttt.computer :refer :all]))

(def empty-board ["-" "-" "-" "-" "-" "-" "-" "-" "-"])

(defn create-input [inputs]
  (apply str (interleave inputs (repeat "\n"))))

(describe "has game loop"
  (it "switches between players"
    (with-in-str (create-input '("0" "1" "2" "3" "5" "4" "6" "7" "8"))
    (should (= (play empty-board 3 :human :computer) "XOX\nOOX\nXO-"))))

  (it "ends game when win"
    (with-in-str (create-input '("0" "1" "2" "3" "4" "5" "6" "7" "8"))
    (should (= (play empty-board 3 :human :computer)"XOX\nOXO\nX--"))))

  (it "creates a Human v Human game given choice 1"
    (with-in-str (create-input '("1" "0" "1" "2" "3" "4" "5" "6" "7" "8"))
    (should (= "XOX\nOXO\nX--" (start-game empty-board 3)))))

  (it "creates a Human v Computer game given choice 2"
    (with-in-str (create-input '("2" "0" "1" "2" "3" "4" "5" "6" "7" "8"))))

  (it "switches players"
    (should (= :computer (switch-player-type :human)))))
