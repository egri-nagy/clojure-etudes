(ns dfs)
;; DEPTH-FIRST SEARCH
;;these two depth-first search algorithms got removed from kigen, but they might
;;be still of interest as different implementations

;; stack: node and action function pairs, the work that still needs to be done
(defn dfs
  [seeds afs]
  (let [todof #(for [af afs] [% af])] ; elt -> pairs [elt af], todo list func
    (loop [stack (into [] (mapcat todof seeds))  orbit (set seeds)]
      (if (empty? stack)
        orbit
        (let [[e af] (peek stack)
              ne (af e)
              nstack (pop stack)]
          (if (contains? orbit ne)
            (recur nstack orbit)
            (recur (into nstack (todof ne)) (conj orbit ne))))))))

;;another variant - environment style
(defn dfs2
  [seeds afs]
  (letfn [(todof [t] (for [af afs] [t af])) ; elt -> pairs [elt af]
          (f [env]
            (let [stack (::stack env)
                  orbit (::orbit env)]
              (if (empty? stack)
                orbit
                (let [[e af] (peek stack)
                      ne (af e)
                      nstack (pop stack)]
                  (if (contains? orbit ne)
                    (recur (assoc env ::stack nstack))
                    (recur (assoc env
                                  ::stack (into nstack (todof ne))
                                  ::orbit (conj orbit ne))))))))]
    (f {::stack (into [] (mapcat todof seeds))
        ::orbit (set  seeds)})))
