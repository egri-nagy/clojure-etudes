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

(defn tuples1
  "Wasteful."
  [n coll]
  (if (= n 0)
    [()]
    (mapcat (fn [x] (map (partial cons x)
                         (tuples1 (dec n) coll)))
         coll)))

(defn tuples
  "All n-tuples formed by the elements of a collection."
  [n coll]
  (if (= n 0)
    [()]
    (let [n-1-tuples (tuples (dec n) coll)]
      (mapcat (fn [x] (map (partial cons x)
                           n-1-tuples))
              coll))))

;;tuples2
(defn put-in-front [coll x]
  (map (partial cons x) coll))

(defn tuples2
  [n coll]
  (if (= n 0)
    [()]
    (mapcat
     (partial put-in-front (tuples2 (dec n) coll))
     coll)))
