(ns etudes.logic
  (:refer-clojure :exclude [==])
  (:require [clojure.core.logic :as l]
            [clojure.core.logic.fd :as fd]))

;; unconditional success
(l/run* [q]
  l/succeed)

(l/run* [q]
  (l/== q q))

;; impossible
(l/run* [q]
  l/fail)

(l/run* [q]
  (l/!= q q))

(l/run* [q]
  (l/== q 1)
  (l/== q 2))

;;illustrating conde as an OR operation vs. the AND by simple enumeration
(l/run* [q]
  (l/fresh [x y z]
    (l/conde
     ((l/== x 1))
     ((l/== y 2))
     ((l/== z 3)))
    (l/== q [x y z])))

(l/run* [q]
  (l/fresh [x y z]
    (l/== x 1)
    (l/== y 2)
    (l/== z 3)
    (l/== q [x y z])))


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

(defn bitstrings [n]
  (let [p (vec (repeatedly n l/lvar))
        bits  (fd/domain 0 1)]
    (l/run* [q]
      (l/everyg #(fd/in % bits) p)
      (l/== q p))))
