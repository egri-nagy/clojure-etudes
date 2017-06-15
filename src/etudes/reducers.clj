(ns etudes.reducers
  (:require [clojure.core.reducers :as r]
            [criterium.core :as c]))

(def v (into [] (range 200000)))

(c/quick-bench (reduce + (map inc (filter even? v))))

(c/quick-bench (reduce + (r/map inc (r/filter even? v))))

(c/quick-bench (r/fold + (r/map inc (r/filter even? v))))


