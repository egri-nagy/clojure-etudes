(ns etudes.palindromes
  "Taken from blog posts
  https://spin.atomicobject.com/2015/12/07/logic-programming-clojure-palindromes/
  and
  https://spin.atomicobject.com/2015/12/14/logic-programming-clojure-finite-domain-constraints/
  with corrections from comments.
  "
  (:require [clojure.core.logic :as cl]
            [clojure.core.logic.fd :as fd]))

(defn reverso [l r]
  (cl/conde
   [(cl/== l ()) (cl/== r ())]
   [(cl/fresh [la ld ldr]
      (cl/conso la ld l)
      (cl/appendo ldr (list la) r)
      (reverso ld ldr))]))

(defn palindromo [v]
  (reverso v v))

(defn everyo [l f]
  (cl/fresh [head tail]
    (cl/conde
     [(cl/== l ())]
     [(cl/conso head tail l)
      (f head)
      (everyo tail f)])))

(defn sumo [l sum]
  (cl/fresh [a d sum-of-remaining]
    (cl/conde
     [(cl/== l ()) (cl/== sum 0)]
     [(cl/conso a d l)
      (fd/+ a sum-of-remaining sum)
      (sumo d sum-of-remaining)])))

(defn find-palindromes-totalling [sum results]
  (let [domain (fd/interval 1 1000)]
    (cl/run results [q]
      (palindromo q)
      (everyo q #(fd/in % domain))
      (sumo q sum))))

(find-palindromes-totalling 20 10)
