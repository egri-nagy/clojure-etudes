(ns etudes.reducers
  (:require [clojure.core.reducers :as r]
            [tesser.core :as t]
            [criterium.core :as c]))

(def CORES (.. Runtime getRuntime availableProcessors))

(def v (into [] (repeatedly 10000  #(rand-int 1000000000))))

(defn divisors [n]
  (into []
        (reduce concat
                (for [x (range 1 (inc (Math/sqrt n)))
                      :when (zero? (rem n x))]
                  [x (/ n x)]))))

;combining function
(defn MAX
  ([] 0)
  ([m x] (max m x)))


;; (println)
;; (println "futures and derefs")

;; (c/bench (let [f1 (future (reduce MAX (map count (map divisors (filter even? (range 200000 201000))))))
;;                f2 (future (reduce MAX (map count (map divisors (filter even? (range 201000 202000))))))
;;                f3 (future (reduce MAX (map count (map divisors (filter even? (range 202000 203000))))))
;;                f4 (future (reduce MAX (map count (map divisors (filter even? (range 203000 204000))))))]
;;            (reduce MAX 0 [@f1 @f2 @f3 @f4])))


;; ;(c/bench (t/tesser (t/chunk 1024 v) (t/reduce MAX (t/map count (t/map divisors (t/filter even? v))))))

 (println)
 (println "tesser")
 (c/bench
  (->> (t/filter even?)
       (t/map divisors)
       (t/map count)
       (t/fold MAX)
       (t/tesser (t/chunk 512 v))))

(println)
(println "vanilla")
(c/bench (reduce MAX (map count (map divisors (filter even? v)))))

(println)
(println "reducers")
(c/bench (r/reduce MAX (r/map count (r/map divisors (r/filter even? v)))))

(println)
(println "folders")
(c/bench (r/fold MAX MAX (r/map count (r/map divisors (r/filter even? v)))))
(println)
