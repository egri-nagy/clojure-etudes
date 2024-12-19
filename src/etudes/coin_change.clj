(ns etudes.coin-change)

(defn coin-change [coins sum]
  (cond
    (zero? sum) 1
    (or (neg? sum) (empty? coins)) 0
    :else (+ (coin-change coins (- sum (first coins)))
             (coin-change (rest coins) sum))))

(coin-change [1 3] 7)