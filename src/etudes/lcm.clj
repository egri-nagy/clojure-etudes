(ns etudes.lcm)

;; not too good, rather imperative thinking
(defn lcm [& nums]
  (loop [nums (vec nums) vnums (vec nums)]
      (if (apply = vnums) (first vnums)
          (let [m (apply min vnums)
                pos (fn [e v] (first(filter #(= (get v %) e)
                                            (range (count v)))))
                i (pos m vnums)]
            (recur nums (assoc vnums i (+ m (get nums i))))))))

(lcm 2/3 5/7)

;;another one
(defn lcm2 [& x]
  (first(first
         (filter (fn [z] (apply = z))
                 (iterate (fn [l]
                            (let [m (apply min l)
                                  diff (map #(if (= m %) %2 0) l x)]
                              (map + l diff)))
                          x)))))
