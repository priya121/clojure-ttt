(ns clojure-ttt.game_spec
  (:require [speclj.core :refer :all]
   [clojure-ttt.game :refer :all]
   [clojure-ttt.computer :refer :all]))

(def empty-board ["-" "-" "-" "-" "-" "-" "-" "-" "-"])

(defn create-input [inputs]
  (apply str (interleave inputs (repeat "\n"))))

(it "it displays inital prompt for game type"
  (should-contain "Enter 1 for HvH\n2 for HvC\n3 for CvH\n 4 for CvC:\n" (start-game)))

(describe "has a console"
  (it "switches between players"
    (with-in-str (create-input '("0" "1" "2" "3" "4" "5" "6" "7" "8"))
    (should-contain "XOX\nOXO\nX--\n" (with-out-str (play-h-h empty-board :human :human)))))

  (it "asks for board size"
    (with-in-str (create-input '("1" "3" "0" "1" "2" "3" "4" "5" "6" "7" "8"))
    (should-contain "Please enter a board size:\n(3 for 3x3 / 4 for 4x4 etc.)" (with-out-str (start-game)))))

  (it "ends game when win"
    (with-in-str (create-input '("0" "1" "2" "3" "4" "5" "6" "7" "8"))
    (should (= (with-out-str (play-h-h empty-board :human :computer)"XOX\nOXO\nX--\n")))))

  (it "creates a Human v Human game given choice 1"
    (with-in-str (create-input '("1" "3" "0" "1" "2" "3" "4" "5" "6" "7" "8"))
    (should-contain "XOX\nOXO\nX--" (with-out-str (start-game)))))

  (it "tells a user which game type they chose"
    (with-in-str (create-input '("1" "0" "1" "2" "3" "4" "5" "6" "7" "8"))
    (should-contain "Human v Human Game\n" (with-out-str (start {:game :hvh}))))))
