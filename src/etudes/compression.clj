(ns etudes.compression)

;tweet by @FPeschanski
(map (juxt first count) (partition-by identity coll))

;answer by @mchampine
(flatten (map (fn [[ch ct]] (repeat ct ch)) rle))
(mapcat (partial apply repeat) rle)
