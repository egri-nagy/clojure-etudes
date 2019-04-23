;; taken from the Joy of Clojure, 2nd edition
(ns etudes.sudoku-logic
  (:refer-clojure :exclude [==])
  (:require [clojure.core.logic :as logic]
            [clojure.core.logic.fd :as fd]
            [etudes.sudoku :refer (print-board prep) :as sudoku]))

(defn rowify [board]
  (->> board
       (partition 9)
       (map vec)
       vec))

(defn colify [rows]
  (apply map vector rows))

(defn subgrid [rows]
  (partition 9
    (for [row (range 0 9 3)
          col (range 0 9 3)
          x (range row (+ row 3))
          y (range col (+ col 3))]
      (get-in rows [x y]))))

(defn init [[lv & lvs] [cell & cells]]
  (if lv
    (logic/fresh []
       (if (= '- cell)
         logic/succeed
         (logic/== lv cell))
       (init lvs cells))
    logic/succeed))

(def logic-board #(repeatedly 81 logic/lvar))

(defn solve-logically [board]
  (let [legal-nums (fd/interval 1 9)
        lvars (logic-board)
        rows  (rowify lvars)
        cols  (colify rows)
        grids (subgrid rows)]
    (logic/run 1 [q]
      (init lvars board)
      (logic/everyg #(fd/in % legal-nums) lvars)
      (logic/everyg fd/distinct rows)
      (logic/everyg fd/distinct cols)
      (logic/everyg fd/distinct grids)
      (logic/== q lvars))))


(def ca '[2 5 8 - 3 - 9 - 1
          - 1 - - - 4 - - -
          4 - 7 9 - - 2 - 8
          3 - 5 2 - - - - -
          - - - - 9 8 1 3 -
          - 4 - - - 3 - - -
          - - - 3 6 - - 7 2
          5 7 - - - - - - 3
          9 - 3 - - - 6 - 4])

(-> ca
    sudoku/prep
    sudoku/print-board)

(-> ca
    solve-logically
    first
    sudoku/prep
    sudoku/print-board)
