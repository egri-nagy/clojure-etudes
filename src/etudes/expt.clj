(ns etudes.expt)

;; exponentiation in logarithmic number of steps, the actual number depends on
;; the number of ones in the binary representation of n (from SICP)
;; using *' to support arbitrary precision
(defn fast-expt [b n]
  (cond (= n 0) 1
        (even? n) (#(*' % %) (fast-expt b (/ n 2)))
        :else (*' b (fast-expt b (- n 1)))))

(fast-expt 2 32)
