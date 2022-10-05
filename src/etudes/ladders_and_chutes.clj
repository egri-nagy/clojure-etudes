(ns etudes.ladders-and-chutes)

(defn f [i]
  (let [roll (inc (rand-int 6))
        landing (+ i roll)]
    (cond
      (= landing 3) 7
      (= landing 6) 2
      (>= landing 10) :finish
      :else landing)))
(defn calc
  [i]
  (/ (reduce + 
             (map
              count
              (take 1000000
                    (repeatedly
                     #(take-while
                       number?
                       (iterate f i)))))) 
     1000000.0))