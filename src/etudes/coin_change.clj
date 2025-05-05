(ns etudes.coin-change)

(defn count-coin-changes [coins sum]
  (cond
    (zero? sum) 1
    (or (neg? sum) (empty? coins)) 0
    :else (+ (count-coin-changes coins (- sum (first coins)))
             (count-coin-changes (rest coins) sum))))

(count-coin-changes [1 3] 7)

(defn coin-changes
  ;; adding the collection for the chosen coins
  ([coins sum] (coin-changes coins sum []))
  ([coins sum changes]
   (cond
     (zero? sum) [changes] ;we made it, we have the sum!
     (or (neg? sum) (empty? coins)) [] ;not enough coins or too big sum
     :else (reduce into []
                   [(let [coin (first coins)]
                      (coin-changes coins (- sum coin)
                                    (conj changes coin)))
                    (coin-changes (rest coins) sum changes)]))))

(coin-changes [1 2 3 5] 10)
