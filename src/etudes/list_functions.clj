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
