(ns etudes.list-functions)

(defn palindrome?
  "Decides whether a sequence is palindrome or not."
  [l]
  (= l (reverse l)))

;; in a sense this does exactly what = and reverse would do
(defn palindrome?2
  "Decides whether a sequence is palindrome or not,
  without using reverse."
  [l]
  (let [v (vec l)
        n (count v)]
    (every? (fn [x] (= (v x)
                       (v (dec (- n x)))))
            (range (int (/ n 2))))))


(defn element-at
  "reimplementing nth"
  [l n]
  (if (zero? n)
    (first l)
    (element-at (rest l) (dec n))))

(defn MAP
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
