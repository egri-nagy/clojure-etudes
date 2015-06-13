(ns etudes.combinatorics)

;; recursive version
(defn permutations
  [coll]
  (letfn [(fix-one-permute-rest [x]
             (map #(cons x %)
                  (permutations (remove #(= x %) coll))))]
    (if (= 1 (count coll))
      [coll]
      (mapcat fix-one-permute-rest coll))))
