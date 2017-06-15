(ns etudes.reducers
  (:require [clojure.core.reducers :as r]
            [criterium.core :as c]))

(def v (into [] (range 9000)))

(defn factors [n]
  (into []
        (reduce concat
                (for [x (range 1 (inc (Math/sqrt n))) :when (zero? (rem n x))]
                  [x (/ n x)]))))


(c/bench (reduce + (map count (map factors (filter even? v)))))

(println)

(c/bench (r/reduce + (r/map count (r/map factors (r/filter even? v)))))

(println)

(c/bench (r/fold + (r/map count (r/map factors (r/filter even? v)))))

(println)
