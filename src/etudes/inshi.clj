;;core.logic solution for 5*5 Inshi No Heya puzzle https://en.wikipedia.org/wiki/Inshi_no_heya 
(ns etudes.inshi
  (:refer-clojure :exclude [==])
  (:require [clojure.core.logic :refer :all]
            [clojure.core.logic.fd :as fd]))

(def b1 '[\a \b \b \c \d
          \a \e \e \f \d
          \g \g \h \f \d
          \i \j \h \k \k
          \i \j \h \l \l])

;;factors of values in a same group
(def hint {:a 6 :b 4 :c 5 :d 40 :e 3 :f 4 :g 15 :h 40 :i 4 :j 10 :k 6 :l 3})

(def logic-board #(repeatedly 25 lvar))

(defn rowify [board]
  (->> board
       (partition 5)
       (map vec)
       vec))

(defn colify [rows]
  (apply map vec rows))

(defn check-factors [board hint]
  (for [x ()]))
      
  

(defn solve [board hint]
  (let [legal-nums (fd/interval 1 5)
        lvars logic-board
        rows (rowify lvars)
        cols (colify rows)]
    (run 1 [q]
      (everyg #(fd/in % legal-nums) lvars)
      (everyg fd/distinct rows)
      (everyg fd/distinct cols)
      (== q lvars))))

