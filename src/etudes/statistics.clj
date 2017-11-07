(defn mean
  [nums]
  (let [sum (apply + nums)
        n (count nums)]
    (/ sum n)))

(def mean2 (comp (partial apply /)
                 (juxt (partial apply +) count)))

(defn median
  [nums]
  (let [ordered (vec (sort nums))
        n (count nums)
        h (dec (int (/ n 2)))
        indices (if (odd? n)
                  [(inc h)]
                  [h (inc h)])]
    (mean (map ordered indices))))
