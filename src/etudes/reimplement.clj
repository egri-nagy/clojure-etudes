(ns etudes.reimplement)

(defn NTH
  "reimplementing nth for sequences"
  [l n]
  (if (zero? n)
    (first l)
    (NTH (rest l) (dec n))))

(defn MAP
  "recursive implementation of map"
  [f coll]
  (if (empty? coll)
    ()
    (cons (f (first coll))
          (MAP f (rest coll)))))

(defn FILTER
  [pred coll]
  (if (empty? coll)
    ()
    (if (pred (first coll))
      (cons (first coll) (FILTER pred (rest coll)))
      (FILTER pred (rest coll)))))

(defn MAP2
  "implementing map by reduce"
  [f coll]
  (reduce (fn [x y] (conj x (f y)))
          []
          coll))

(defn FILTER2
  "implementing filter by reduce"
  [pred coll]
  (reduce (fn [acc x] (if (pred x) (conj acc x) acc))
          []
          coll))

(defn REDUCE
  [rf acc coll]
  (if (empty? coll)
    acc
    (recur rf (rf acc (first coll)) (rest coll))))
