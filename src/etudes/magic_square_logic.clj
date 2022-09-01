;; modified Sudoku solver
(ns etudes.magic-square-logic
  (:refer-clojure :exclude [==])
  (:require [clojure.core.logic :as logic]
            [clojure.core.logic.fd :as fd]))

(defn rowify [board]
  (->> board
       (partition 3)
       (map vec)
       vec))

(defn diagonal1 [rows]
  (for [x [0 1 2]]
    (nth (nth rows x) x)))

(defn diagonal2 [rows]
  (for [x [0 1 2]]
    (nth (nth rows x) (- 2 x))))

;; transpose
(defn colify [rows]
  (apply map vector rows))

(defn bind [var hint]
  (if (zero? hint)
    logic/succeed
    (logic/== var hint)))

(defn bind-all [vars hints]
  (logic/and* (map bind vars hints)))

(defn sumo
  "This goal succeeds if adding up the elements of l is sum."
  [l sum]
  (logic/fresh [head tail sum-of-remaining]
    (logic/conde
     [(logic/== l ()) (logic/== sum 0)]
     [(logic/conso head tail l)
      (fd/+ head sum-of-remaining sum)
      (sumo tail sum-of-remaining)])))

(defn solve-logically [board]
  (let [legal-nums (fd/interval 1 99)
        lvars (repeatedly 9 logic/lvar)
        rows  (rowify lvars)
        cols  (colify rows)
        d1 (diagonal1 rows)
        d2 (diagonal2 rows)]
    (logic/run* [q]
      (bind-all lvars board)
      (logic/everyg #(fd/in % legal-nums) lvars)
      (logic/everyg #(sumo % 99) rows)
      (logic/everyg #(sumo % 99) cols)
      (sumo d1 99)
      (sumo d2 99)
      (logic/== q lvars))))

(defn print-magic-square
  [msq]
  (map println (rowify msq)))

;(def sols (solve-logically [0 0 0  0 0 0 0 0 0]))

