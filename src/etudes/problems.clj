(ns etudes.problems)

;; Taken from a Haskell exercise, hence the terminology.
;; Write a function that, given a list of integers, finds the subsequence
;; of those integers that sums to a value higher than any other subsequence.
;; For example, given the list:
;; [-2, 1, -3, 4, -1, 2, 1, -5, 4]
;; the subsequence with the maximum sum is [4, -1, 2, 1] which sums to 6.
;; There are no other subsequences in the list that sum to value that is 6
;; or greater.

(defn max-subseq
  "Finds maximal consecutive subsequence in a sequence of ints. It enumerates
  all subsequences. O(n^2) algorithm."
  [ints]
  (let [n (count ints)
        index-pairs (for [i (range n) ; lazy seq of all start-end pairs
                          j (range i n)]
                      [i j])
        subseq (fn [[i j]] ; extracting a subsequence
                 (take j (drop i ints)))
        max-pair (apply max-key #(apply + (subseq %)) index-pairs)]
    (subseq max-pair)))

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
  "Removes invalid characters."
  [s]
  (apply str (filter (set "0123456789.") s)))

(defn posint->english
  [n]
  (let [digits {0 "zero" 1 "one" 2 "two" 3 "three" 4 "four" 5 "five" 6 "six" 7 "seven"
                8 "eight" 9 "nine" }
        teens {}]))

(defn dollars
  [s])
