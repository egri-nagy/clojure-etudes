(ns etudes.list-functions)

(defn palindrome?
  "Decides whether a sequence is palindrome or not."
  [l]
  (= l (reverse l)))

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
