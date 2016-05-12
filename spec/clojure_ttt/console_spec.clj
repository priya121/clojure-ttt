(ns clojure-ttt.console_spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.console :refer :all]))

(defn create-input [inputs]
  (apply str (interleave inputs (repeat "\n"))))

(describe "has a console"
  (it "tests the output of prompt"
    (should (= "Enter a position from 0 - 8: \n"
      (with-out-str (show-initial-prompt)))))

  (it "gets user input"
    (should (= "6" (with-in-str "6\n" (take-input)))))

  (it "converts user input to digit"
    (should (= 0 (with-in-str "0\n" (digit-input)))))

  (it "prompts user to enter a valid number"
    (should (= "Enter a valid number: \n" (with-out-str (with-in-str (create-input '("11" "4")) (valid(digit-input)))))))

  (it "ensures the user enters number on the board"
    (should (= 4 (with-in-str (create-input '("11" "100" "4")) (valid (digit-input)))))))

(run-specs)

