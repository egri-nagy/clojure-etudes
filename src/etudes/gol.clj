(ns etudes.gol
  "Conway's Game of Life, based on Christoph Grand's implementation in
 Clojure Programming, 2012 ISBN: 9781449394707")

(defn neighbours
  "Giving the 8 neighbours."
  [[x y]]
  (for   [dx [-1 0 1]
          dy [-1 0 1]
          :when (not= 0 dx dy)]
    [(+ dx x) (+ dy y)]))

(defn allowed-indices
  "Based on the size (of one dimension) we give a map
   that can be used to get the allowed coordinates"
  [N]
  (if (= N 1)
    {0 [0]}
    {0 [0 1], (dec N) [-1 0]}))

(defn neighbours-finite
  "Giving the 8 neighbours on a finite grid."
  [dx-map dy-map [x y]]
  (for   [dx (dx-map x [-1 0 1])
          dy (dy-map y [-1 0 1])
          :when (not= 0 dx dy)]
    [(+ dx x) (+ dy y)]))

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

(def gol-finite-6x4
  (partial gol-step (partial neighbours-finite
                         (allowed-indices 6)
                         (allowed-indices 4))))