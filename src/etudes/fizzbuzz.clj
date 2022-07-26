(ns etudes.fizzbuzz
  "The classic interview question: FizzBuzz."
  (:require [clojure.core.match :refer [match]]))

(defn fizzbuzz
  "Generating fizz-buzz sequence from 1 up to limit, using conditionals."
  [lim]
  (map (fn [n]
	       (cond
		       (zero? (mod n 15)) "FizzBuzz"
		       (zero? (mod n 3)) "Fizz"
		       (zero? (mod n 5)) "Buzz"
		       :else n))
	     (range 1 lim)))

(defn fizzbuzz2
  "Generating fizz-buzz sequence from 1 up to limit, using pattern matching."
  [lim]
 (for [n (range 1 lim)]
  (match [(mod n 3) (mod n 5)]
      [0 0] "FizzBuzz"
      [0 _] "Fizz"
      [_ 0] "Buzz"
      :else n)))
