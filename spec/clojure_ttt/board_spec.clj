(ns clojure-ttt.board_spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.board :refer :all]))

(def empty-board ["-" "-" "-"
                  "-" "-" "-"
                  "-" "-" "-"])

(def two-moves ["X" "O" "-"
                "-" "-" "-"
                "-" "-" "-"])

(def three-moves["X" "O" "X"
                 "-" "-" "-"
                 "-" "-" "-"])

(def empty-four-by-four["-" "-" "-" "-"
                        "-" "-" "-" "-"
                        "-" "-" "-" "-"
                        "-" "-" "-" "-"])

(def four-by-four["X" "X" "X" "O"
                  "-" "-" "O" "O"
                  "-" "-" "-" "-"
                  "X" "X" "X" "X"])

(def x-win ["X" "O" "X"
            "O" "X" "X"
            "X" "O" "O"])

(def o-win ["O" "X" "O"
            "X" "O" "X"
            "X" "X" "O"])

(describe "can create, display and mark a board"
  (it "can create a 3x3 board"
    (should (= empty-board (with-in-str "3\n" (create-board)))))

  (it "can create a 4x4 board"
    (should (= empty-four-by-four (with-in-str "4\n" (create-board)))))

  (it "can mark a board with X"
    (should (= (mark empty-board 0 "X") ["X" "-" "-" "-" "-" "-" "-" "-" "-"])))

  (it "can mark a board with O"
    (should (= (mark empty-board 8 "O") ["-" "-" "-" "-" "-" "-" "-" "-" "O"])))

  (it "can split board into rows"
    (should (=  "---\n---\n---\n" (with-out-str (display empty-board))))))

  (it "can get indices for board"
    (should (= '([0 "-"] [1 "-"] [2 "-"] [3 "-"] [4 "-"] [5 "-"] [6 "-"] [7 "-"] [8 "-"]) (get-indices empty-board))))

(describe "holds information about the board"
  (it "can tell when board full"
   (should (= false (board-not-full x-win))))

  (it "can tell when board is not full"
   (should (= true (board-not-full empty-board))))

  (it "calculates if it is player X turn"
    (should (= "X" (calculate-player empty-board))))

  (it "calculates if it is player O turn"
    (should (= "O" (calculate-player three-moves))))

  (it "calculates rows for 3x3 board"
    (should (= [[0 1 2] [3 4 5] [6 7 8]] (row-indices 3))))

  (it "calculates rows for 4x4 board"
    (should (= [[0 1 2 3] [4 5 6 7] [8 9 10 11] [12 13 14 15]] (row-indices 4))))

  (it "calculates columns for 3x3 board"
    (should (= [[0 3 6] [1 4 7] [2 5 8]] (column-indices 3))))

  (it "calculates diagonals for 3x3 board"
    (should (= [[2 4 6] [0 4 8]] (diagonal-indices 3))))

  (it "calculates diagonals for 4x4 board"
    (should (= [[3 6 9 12] [0 5 10 15]] (diagonal-indices 4))))

  (it "combines all the winning combinations"
    (should (= [[0 1 2] [3 4 5] [6 7 8] [0 3 6] [1 4 7] [2 5 8] [2 4 6] [0 4 8]] (winning-combo empty-board 3))))

  (it "finds indices for given mark"
    (should (= #{0 2 4 5 6} (positions "X" x-win))))

 (it "find no win for X"
   (should (= false (win? "X" empty-board))))

 (it "find win for X on 3x3"
   (should (= true (win? "X" x-win))))

 (it "find win for X on 4x4"
   (should (= true (win? "X" four-by-four))))

 (it "displays winner X"
   (should (= true (contains? #{(with-out-str(any-win? x-win ))} "X is the winner!\n"))))

 (it "displays winner O"
   (should (= true (contains? #{(with-out-str(any-win? o-win ))} "O is the winner!\n"))))

 (it "only marks empty"
   (should-contain "Please choose an empty square: \n" (with-out-str (with-in-str "4\n" (empty-position three-moves 0))))))

(run-specs)
