;; modified Sudoku solver
(ns etudes.magic-square-logic
  (:refer-clojure :exclude [==])
  (:require [clojure.core.logic :as logic]
            [clojure.core.logic.fd :as fd]))

(defn rowify
  "Creating the rows from the single sequence representation of the
  magic square."
  [msq]
  (->> msq
       (partition 3)
       (map vec)
       vec))

(defn colify
  "Creating the columns based on the rows."
  [rows]
  (apply map vector rows))

(defn diagonal1 [rows]
  (for [x [0 1 2]]
    (nth (nth rows x) x)))

(defn diagonal2 [rows]
  (for [x [0 1 2]]
    (nth (nth rows x) (- 2 x))))

(defn bind-hint
  "Unify the existing entry (a hint) with the corresponding logic variable.
  If the entry is empty, then we give succeed, as the constraints are
  defined elsewhere."
  [var hint]
  (if (zero? hint)
    logic/succeed
    (logic/== var hint)))

(defn bind-all-hints
  [vars hints]
  (logic/and* (map bind-hint vars hints)))

(defn sumo
  "This goal succeeds if adding up the elements of l is sum."
  [l sum]
  (logic/fresh [head tail sum-of-remaining]
    (logic/conde
     [(logic/== l ()) (logic/== sum 0)]
     [(logic/conso head tail l)
      (fd/+ head sum-of-remaining sum)
      (sumo tail sum-of-remaining)])))

(defn solve-logically [msq]
  (let [legal-nums (fd/interval 1 99)
        lvars (repeatedly 9 logic/lvar)
        rows  (rowify lvars)
        tuples (concat rows (colify rows) [(diagonal1 rows) (diagonal2 rows)])]
    (logic/run* [q]
      (bind-all-hints lvars msq)
      (logic/everyg #(fd/in % legal-nums) lvars)
      (logic/everyg #(sumo % 99) tuples)
      (logic/== q lvars))))

(defn print-magic-square
  [msq]
  (doseq [row (rowify msq)]
    (println row)))

;(def sols (solve-logically [0 0 0  0 0 0 0 0 0]))

