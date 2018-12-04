(ns etudes.palindromes
  "Taken from blog posts
  https://spin.atomicobject.com/2015/12/07/logic-programming-clojure-palindromes/
  and
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


(cl/run 10 [q]
  (palindromo q))
