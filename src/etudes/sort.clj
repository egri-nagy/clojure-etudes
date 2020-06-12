(ns etudes.sort)

(defn merge-sort
  "sorting the given collection with merge-sort"
  [coll]
  (if (or (empty? coll) (= 1 (count coll)))
    coll
    (let [[l1 l2] (split-at (/ (count coll) 2) coll)]
      ;recursive call
      (loop [r [] l1 (merge-sort l1) l2 (merge-sort l2)]
        ;merging
        (cond (empty? l1) (into r l2) ;when l1 is exhausted
              (empty? l2) (into r l1) ;when l2 is exhausted
              :else (if (neg? (compare (first l1) (first l2))) ;comparison
                      (recur (conj r (first l1)) (rest l1) l2)
                      (recur (conj r (first l2)) l1 (rest l2))))))))

(defn quick-sort
  "sorting the given collection with quick-sort (functional style)"
  [coll]
  (if (or (empty? coll) (= 1 (count coll)))
    coll
    (let [pivot (first coll)
          greater (filter #(>= % pivot) (rest coll))
          smaller (filter #(< % pivot) (rest coll))]
      (concat (quick-sort smaller) [pivot] (quick-sort greater)))))
