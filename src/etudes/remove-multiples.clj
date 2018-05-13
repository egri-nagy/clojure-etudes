(ns etudes.remove-multiples
  (:require [clojure.core.match :refer [match]]))

;; Write a function that, given a list of integers, removes all values that
;; are a multiple of 5 or multiple of 7, but not those values which are a
;; multiple of 35.

(defn remove-multiples-logic
  "Removes from a sequence of ints those that are divisible by 5 or 7
  but not by 35. Using conditionals."
  [ints]
  (remove (fn [x]
            (let [div5? (zero? (mod x 5))
                  div7? (zero? (mod x 7))]
              (and (or div5? div7?)
                   (not (and div5? div7?)))))
          ints))

(defn remove-multiples-matching
  "Removes from a sequence of ints those that are divisible by 5 or 7
  but not by 35. It uses pattern matching."
  [ints]
  (filter (fn [x]   ; this return truthy/falsey values
            (match [(mod x 5) (mod x 7)]
                   [0 0] x
                   [0 _] nil
                   [_ 0] nil
                   :else x))
          ints))
