(ns etudes.parens)


(defn pars
  "All well-formed sequences of 2n parentheses."
  ([n] (set (map (partial apply str) (pars n 0 0 []))))
  ([n o c l]
   (if (= (count l) (* 2 n)) [l]
       (concat (if (< o n) (pars n (inc o) c (conj l "(" )))
               (if (< c o) (pars n o (inc c) (conj l ")" )))))))

(pars 3)
