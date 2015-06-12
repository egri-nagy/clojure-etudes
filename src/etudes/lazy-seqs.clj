(ns etudes.lazy-seqs)

;; Playing with lazily evaluated infinite lists.
;; Recipe: construct your list recursively, then call lazy-seq on that.

;; a function that return a lazy list of the natural numbers
(defn naturals
  ([] (naturals 0))
  ([n] (lazy-seq (cons n (naturals (inc n))))))

;; it is a function, so you have to call it to get the list
(take 42 (naturals))

;; can we do it without lazy-seq? yes!
(take 42 (iterate inc 0))

;; a lazy list of the rationals with repetitions
;; enumerated by the zig-zag diagonal method
;; defining a step function and using iterate to create lazy list
;; m - enumerator, n - denominator, dir - direction
(defn repetitive-rationals
  []
  (let [step
        (fn [[m n dir]]
          (cond (and (= dir :down) (= m 1)) [m (inc n) :up]
                (and (= dir :up) (= n 1))   [(inc m) n :down]
                (= dir :up)                 [(inc m) (dec n) :up]
                :else                       [(dec m) (inc n) :down]))]
    (map (fn [[m n]] (/ m n))
         (iterate step [1 1 :down]))))

(defn pascal-triangle
  [v]
  (concat [1]  (map #(apply + %) (partition 2 1 v)) [1]))

(take 5 (iterate pascal-triangle [1]))
