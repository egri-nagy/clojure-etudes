(ns etudes.ladders-and-chutes)

(defn f [i]
  (let [roll (inc (rand-int 6))
        landing (+ i roll)]
    (cond
      (= landing 3) 7
      (= landing 6) 2
      (>= landing 10) :finish
      :else landing)))

(/ (reduce + (map count (take 10000 (repeatedly #(take-while number? (iterate f 1)))))) 10000.0)