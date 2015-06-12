(ns etudes.logic
  (:refer-clojure :exclude [==])
  (:require [clojure.core.logic :as l]
            [clojure.core.logic.fd :as fd]))

(l/run* [q]
  (l/fresh [x y]
    (l/== q [x y])))
