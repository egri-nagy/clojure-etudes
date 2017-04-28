(ns etudes.halving-method
  "finding roots of real functions, SICP 1.3.3")

(defn find-root
  "Finds a root of a function when given two x values, one with
  f(x)<0 and another f(x)>0. Checks for this property, if fails, gives nil."
  [f neg-point pos-point]
  (when (and (neg? (f neg-point)) (pos? (f pos-point)))
    (let [midpoint (/ (+ neg-point pos-point) 2)
          midval (f midpoint)]
      (cond
        (< (Math/abs midval) 0.00001) midpoint
        (pos? midval) (find-root f neg-point midpoint)
        :else (find-root f midpoint pos-point)))))

(find-root #(Math/sin %) 4 3) ; finds PI
