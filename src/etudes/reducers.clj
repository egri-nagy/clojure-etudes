(ns etudes.reducers
  (:require [clojure.core.reducers :as r]
            [criterium.core :as c]))

(def v (into [] (range 200000 205000)))

(defn factors [n]
  (into []
        (reduce concat
                (for [x (range 1 (inc (Math/sqrt n))) :when (zero? (rem n x))]
                  [x (/ n x)]))))


(defn MAX
  ([] 0)
  ([m x] (max m x)))

(println)
(println "vanilla")

(c/bench (reduce MAX (map count (map factors (filter even? v)))))

(println)
(println "reducers")

(c/bench (r/reduce MAX (r/map count (r/map factors (r/filter even? v)))))


(println)
(println 1024)

(c/bench (r/fold 1024 MAX MAX (r/map count (r/map factors (r/filter even? v)))))

(println)
(println 512)

(c/bench (r/fold 512 MAX MAX (r/map count (r/map factors (r/filter even? v)))))

(println)
(println 64)

(c/bench (r/fold 64 MAX MAX (r/map count (r/map factors (r/filter even? v)))))


(println)
