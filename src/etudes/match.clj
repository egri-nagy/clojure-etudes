(ns etudes.match
  (:require [clojure.core.match :refer [match]]))

;; The classic fizzbuzz problem.

(defn fizzbuzz
  "Generating fizz-buzz sequence from 1 up to limit."
  [lim]
 (for [n (range 1 lim)]
  (match [(mod n 3) (mod n 5)]
      [0 0] "FizzBuzz"
      [0 _] "Fizz"
      [_ 0] "Buzz"
      :else n)))
