(ns etudes.reducers
  (:require [clojure.core.reducers :as r]))

(def v (into [] (range 2000000)))

(time (reduce + (map inc (filter even? v))))y

(time (reduce + (r/map inc (r/filter even? v))))

(time (r/fold + (r/map inc (r/filter even? v))))


