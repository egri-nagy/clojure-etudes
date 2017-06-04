(ns etudes.combinatorics)

;; recursive version
(defn permutations
  [coll]
  (if (empty? coll)
    [coll]
    (mapcat (fn [x] ;fix x permute rest
              (map (partial cons x)
                   (permutations (remove (partial = x) coll))))
            coll)))
