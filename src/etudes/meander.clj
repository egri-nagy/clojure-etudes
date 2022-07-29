(ns etudes.meander (:require [meander.epsilon :as m]))

(def my-data {:x 1 :y 2 :z 3})

(defn manual-transform
  [d]
  {:x (:x d)
   :non-xs [(:y d) (:z d)]})

(defn transform
  [d]
  (m/match
   d
   {:x ?x, :y ?y, :z ?z}
   {:x ?x, :non-xs [?y ?z]}))
