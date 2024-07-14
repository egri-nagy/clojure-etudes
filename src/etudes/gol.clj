(ns etudes.gol
  "Conway's Game of Life, based on Christoph Grand's implementation in
 Clojure Programming, 2012 ISBN: 9781449394707"
  (:require [clojure.math.combinatorics :as combo]
            [clojure.string :as string]))

;; NEIGHBOURS ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn neighbours
  "Giving the 8 neighbours on an infinite 2D grid."
  [[x y]]
  (for   [dx [-1 0 1]
          dy [-1 0 1]
          :when (not= 0 dx dy)]
    [(+ dx x) (+ dy y)]))

;; for the finite grid we need a some trick
(defn allowed-indices
  "Based on the size (of one dimension) we give a hash-map
   that can be used to get the allowed coordinate deltas.
   The default value for lookup is meant to be [-1 0 1]."
  [N]
  (if (= N 1)
    {0 [0]} ;for the extreme case of single cell
    {0 [0 1], (dec N) [-1 0]}))

(defn neighbours-finite
  "Giving the 8 neighbours on a finite grid."
  [dx-map dy-map [x y]]
  (for   [dx (dx-map x [-1 0 1])
          dy (dy-map y [-1 0 1])
          :when (not= 0 dx dy)]
    [(+ dx x) (+ dy y)]))

(defn neighbours-toroidal
  "Giving the 8 neighbours on a finite grid."
  [width height [x y]]
  (distinct (for [dx [-1 0 1]
                  dy [-1 0 1]
                  :when (not= 0 dx dy)]
              [(mod (+ dx x) width)
               (mod (+ dy y) height)])))

;; The Game of Life step (update) function ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn gol-step
  "Yields the next state of the world. The input is a set of living cells
  represented by pairs of coordinates (vectors). The output is in the same
  format. The algorithm is a bit counterintuitive: it counts how many times
  a cell is counted as a neighbour of some living cell."
  [neighbours-fn cells]
  (set (for [[loc n] (frequencies (mapcat neighbours-fn cells))
             :when (or (= n 3)
                       (and (= n 2) (cells loc)))]
         loc)))

;; now we can define different gol instances through customized step functions
(defn gol-finite-step-fn
  "Creates a step function for a finite rectangular grid with the given
   dimensions."
  [width height]
  (partial gol-step (partial neighbours-finite
                         (allowed-indices width)
                         (allowed-indices height))))

(defn gol-toroidal-step-fn
  "Creates a step function for a finite toroidal grid with the given
   dimensions."
  [width height]
  (partial gol-step (partial neighbours-toroidal width height)))

;; Drawing and erasing step functions ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn set-cell-fn
  "Just adding another living cell to the living cells."
  [cell]
  (fn [cells] (conj cells cell)))

(defn erase-cell-fn
  "Just removing a cell."
  [cell]
  (fn [cells] (disj cells cell)))

;; Making global transformations ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; two representations
; bitstrings, i.e. characteristic functions
; set of cells (coordinate pairs)
(defn bin2dec
  "Takes a sequence of bits, zeroes and ones, and produces its decimal
   integer value."
  [bits]
  (reduce
   (fn [sum i] (+ i (* 2 sum)))
   0
   bits))

(defn selector
  "Based on the supplied bits, it selects elements from a sequential collection.
   The element is in if the corresponding bit is 1."
  [things bits]
  (set
   (filter identity ;removig nils
           (map (fn [thing bit]
                  (when (= bit 1) thing))
                things bits))))

(defn gol-transformations
  "creating all transformations (toroidal)"
  [width height]
  (let [num-of-cells (* width height)
        num-of-states (int (Math/pow 2 num-of-cells))
        bitstrings (apply combo/cartesian-product (repeat num-of-cells [0 1]))
        cells (sort (map vec (combo/cartesian-product (range width)
                                                      (range height))))
        cells2vals (zipmap (reverse cells)
                           (iterate (partial * 2) 1)) ;powers of 2
        ;;creates a hash-map of state -> updated state entries
        mappings-fn (fn [step-fn]
                      (into {}
                            (map (fn [bits]
                                   [(bin2dec bits)
                                    (reduce
                                     +
                                     (map cells2vals
                                          (step-fn (selector cells bits))))])
                                 bitstrings)))
        trans-fn (fn [mappings]
                   (mapv mappings (range num-of-states)))
        steppers (into [(gol-toroidal-step-fn width height)]
                       (concat (map set-cell-fn cells)
                               ;(map erase-cell-fn cells)
                               ))]
    (mapv (comp trans-fn mappings-fn) steppers)))

;;example
(gol-transformations 2 2)


(defn GapTransformation
  "Converting a plain 0-indexed transformation vector to GAP commands."
  [t]
  (str "Transformation(["
       (string/join "," (map inc t))
       "])"))

(defn GapTransformations
  "Converting transformation to GAP generator set."
  [ts]
  (str "["
       (string/join "," (map GapTransformation ts))
       "];"))

(GapTransformations (gol-transformations 2 3))