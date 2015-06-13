(ns etudes.combinatorics)

;; recursive version
(defn permutations
  [coll]
  (if (= 1 (count coll))
    [coll]
    (mapcat
     (fn [x] (map
              #(cons x %)
              (permutations (remove #(= x %) coll))))
     coll)))
