(ns etudes.dollars
  (:require [clojure.string :as string]))

;; Write a function (dollars) that accepts a `String` and returns a `String`.
;; It will accept a numeric value as input, representing an amount of
;; money, and the function will convert that value to its how it is written
;; in English. Use any preferred programming language.
;; For example, the input "1.11" will result in a return value of "one
;; dollar and eleven cents"
;; Invalid characters should be ignored, meaning that every input string
;; has an output string.
;; The empty string produces "zero dollars and zero cents"
;; Some other example input and output:
;;     > dollars "0"
;;     "zero dollars and zero cents"
;;     > dollars "1"
;;     "one dollar and zero cents"
;;     > dollars "0.1"
;;     "zero dollars and ten cents"
;;     > dollars "1."
;;     "one dollar and zero cents"
;;     > dollars "0."
;;     "zero dollars and zero cents"
;;     > dollars ".34"
;;     "zero dollars and thirty-four cents"
;;     > dollars "0.3456789"
;;     "zero dollars and thirty-four cents"
;;     > dollars "1.0"
;;     "one dollar and zero cents"
;;     > dollars "1.01"
;;     "one dollar and one cent"
;;     > dollars "1000456.13"
;;     "one million four hundred and fifty-six dollars and thirteen cents"

(defn sanitize
  "Removes invalid characters from a string representation of a floating point
  number."
  [s]
  (apply str (filter (set "0123456789.") s)))

(defn posint->english
  "English writing of positive integers up to millions."
  [n]
  (let [;a map from the digits and the still irregular teens to words
        smallnums {0 "zero" 1 "one" 2 "two" 3 "three" 4 "four" 5 "five" 6 "six"
                   7 "seven" 8 "eight" 9 "nine"
                   10 "ten" 11 "eleven" 12 "twelve" 13 "thirteen" 14 "fourteen"
                   15 "fifteen" 16 "sixteen" 17 "seventeen" 18 "eighteen"
                   19 "nineteen"}
        ;the are also irregular, so they have a separate map
        tens {2 "twenty" 3 "thirty" 4 "forty" 5 "fifty" 6 "sixty" 7 "seventy"
              8 "eighty" 9 "ninety"}]
    (cond
      ;just a lookup
      (< n 20) (smallnums n)
      ;up to a hundred we use the dash to connect
      (< n 100) (str (tens (int (/ n 10)))
                     "-"
                     (smallnums (mod n 10)))
      ;up to a thousand, we still only multiply by 10
      (< n 1000) (let [rem (mod n 100)
                       ending (if (zero? rem)
                                " hundred"
                                (str " hundred and "
                                     (posint->english rem)))]
                   (str (smallnums (int (/ n 100))) ending))
      ;up to million we jump up by 10^3, so double recursion
      (< n 1000000) (let [rem (mod n 1000)
                          ending (cond (zero? rem) " thousand"
                                       (> rem 100 ) (str " thousand "
                                                         (posint->english rem))
                                       :else (str " thousand and "
                                                  (posint->english rem)))]
                      (str (posint->english (int (/ n 1000))) ending))
      ;exactly the same pattern, abstraction to be made when going to billion
      :else (let [rem (mod n 1000000)
                  ending (cond (zero? rem) " million"
                               (> rem 100) (str " million "
                                                (posint->english rem))
                               :else (str " million and "
                                          (posint->english rem)))]
              (str (posint->english (int (/ n 1000000))) ending)))))

(defn dollars
  [s]
  (let [; bisecting the string about the possibly non-existent .
        ds (apply str (vec (take-while (complement #{\.}) s)))
        cs  (apply str (vec (take 2 (rest (drop-while (complement #{\.}) s)))))
        ; correcting cents, e.g. .1 should produce 10 cents
        ccs (apply str cs (repeat (- 2 (count cs)) \0))
        ; ds might be empty (unlike corrected ccs)
        dn (if (empty? ds)
             0
             (read-string ds))
        cn (read-string ccs)]
    (str (posint->english dn)
         (if (= 1 dn)
           " dollar"
           " dollars")
         " and "
         (posint->english cn)
         (if (= 1 cn)
           " cent"
           " cents"))))
