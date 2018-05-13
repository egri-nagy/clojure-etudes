(ns max-sum-subsequence)

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
