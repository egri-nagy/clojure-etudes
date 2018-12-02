(ns etudes.sgf
  "Functions for working with the Smart Game Format"
  (:require [clojure.string :as string]))

  (def letters "abcdefghijklmnopqrstuvwxyz")

(def letter->coord (zipmap letters (range 1 (inc (count letters)))))

(defn sgfcoords->igocoords
  [s]
  (let [coords (re-seq #"[a-z]+" s)
        converted (map (fn [cc] (str (first cc)
                                     (letter->coord (second cc)))) coords)]
    (string/join "," converted)))
