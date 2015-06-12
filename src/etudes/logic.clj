(ns etudes.logic
  (:refer-clojure :exclude [==])
  (:require [clojure.core.logic :as l]
            [clojure.core.logic.fd :as fd]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;PROBLEM: construct permutations of n points
;;IDEA: we need all vectors of size n with no duplications

;;it is easy to do permutations of fixed size
(defn permutations3points []
  (l/run* [q]
    (l/fresh [x y z]
      (fd/in x y z (fd/interval 1 3))
      (fd/distinct [x y z])
      (l/== q [x y z]))))

;;let's do it for arbitrary number of points -- even easier! :)
(defn permutations [n]
  (let [p (vec (repeatedly n l/lvar))
        points  (fd/interval 1 n)]
    (l/run* [q]
      (l/everyg #(fd/in % points) p)
      (fd/distinct p)
      (l/== q p))))
