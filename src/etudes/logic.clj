(ns etudes.logic
  (:refer-clojure :exclude [==])
  (:require [clojure.core.logic :as l]
            [clojure.core.logic.fd :as fd]))

;; RS Reasoned Schemer


(l/run* [q]
  (l/== q 2))

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
    (l/== x 1)
    (l/== y 2)
    (l/== z 3)
    (l/== q [x y z])))

(l/run* [q]
  (l/fresh [x y z]
    (l/conde
     ((l/== x 1))
     ((l/== y 2))
     ((l/== z 3)))
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

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;PROBLEM: find all bitstrings of length n
(defn bitstrings [n]
  (let [p (vec (repeatedly n l/lvar))
        bits  (fd/domain 0 1)]
    (l/run* [q]
      (l/everyg #(fd/in % bits) p)
      (l/== q p))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;PROBLEM: find all subseqs of a seq
;;from http://objectcommando.com/blog/2011/10/13/appendo-the-great/
(defn sublisto
  "The list x (in sequence) appears in y"
  [x y]
  (l/fresh [a b c]
    (l/appendo a b y)
    (l/appendo x c b)))

;;just getting 7, also demonstrating the redundancy of the solution set
(l/run 7 [q]
  (sublisto q [1 :a 42 "foo"]))

(defn sublists [l]
  (set (l/run* [q]
         (sublisto q l))))

;;plain functional style
(defn sublists2 [l]
  (if (empty? l)
    [()]
    (concat
     (for [i (range 1 (inc (count l)))] (take i l))
     (sublists2 (rest l)))))

;;RS 2/29
(l/run* [q]
  (l/fresh [d x y w s]
    (l/conso w [\a \n \s] s)
    (l/resto q s)
    (l/firsto q x)
    (l/== \b x)
    (l/resto q d)
    (l/firsto d y)
    (l/== \e y)))

;; RS 3/31
(defn twinso
  [l]
  (l/fresh [x y]
    (l/conso x y l)
    (l/conso x [] y)))

(l/run* [q]
  (twinso [q "tofu"]))

;;without twinso
(l/run* [q]
  (l/fresh [x]
    (l/== q [x x])))

;; reduce as a relation

;; https://www.reddit.com/r/Clojure/comments/5dhw01/corelogic_could_you_code_review_my_reduceo/
(defn reduceo-plain
  [relo initial coll result]
  (l/conde
   [(l/emptyo coll) (l/== result initial)]
   [(l/fresh [next-item
              remaining
              result-so-far]
             ;; the next item comes from the collection to be processed 
             (l/conso next-item remaining coll)
             ;; the reduction step works
             (relo initial next-item result-so-far)
             ;; processing the remaining items will produce the result
             (reduceo-plain relo result-so-far remaining result))]))

;; https://stackoverflow.com/questions/47185552/why-does-this-implementation-of-sorto-does-not-terminate
(l/defne reduceo
  [relo initial coll result]
  ([_ _ () _] (l/== initial result))
  ([_ _ [next-item . remaining] _]
   (l/fresh [result-so-far]
          (relo initial next-item result-so-far)
          (reduceo relo result-so-far remaining result))))