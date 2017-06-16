(ns etudes.reducers
  (:require [clojure.core.reducers :as r]
            [tesser.core :as t]
            [criterium.core :as c]))

(def v (into [] (range 200000 204000)))

(defn factors [n]
  (into []
        (reduce concat
                (for [x (range 1 (inc (Math/sqrt n))) :when (zero? (rem n x))]
                  [x (/ n x)]))))


(defn MAX
  ([] 0)
  ([m x] (max m x)))


(println)
(println "futures and derefs")

(c/bench (let [f1 (future (reduce MAX (map count (map factors (filter even? (range 200000 201000))))))
               f2 (future (reduce MAX (map count (map factors (filter even? (range 201000 202000))))))
               f3 (future (reduce MAX (map count (map factors (filter even? (range 202000 203000))))))
               f4 (future (reduce MAX (map count (map factors (filter even? (range 203000 204000))))))]
           (reduce MAX 0 [@f1 @f2 @f3 @f4])))

(println)
(println "tesser")

;(c/bench (t/tesser (t/chunk 1024 v) (t/reduce MAX (t/map count (t/map factors (t/filter even? v))))))

(c/bench
 (->> (t/filter even?)
      (t/map factors)
      (t/map count)
      (t/fold MAX)
      (t/tesser (t/chunk 1024 v))))


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
