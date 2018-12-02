(ns etudes.sgf
  "Functions for working with the Smart Game Format"
  (:require [clojure.string :as string]))

(def letters "abcdefghijklmnopqrstuvwxyz")
  (def noiletters "abcdefghjklmnopqrstuvwxyz")

(def letter->coord (zipmap letters (range 1 (inc (count letters)))))

(def letter->noiletter (zipmap letters noiletters))

(defn sgfcoords->igocoords
  [s]
  (let [coords (re-seq #"[a-z]+" s)
        converted (map (fn [cc] (str (letter->noiletter (first cc))
                                     (letter->coord (second cc)))) coords)]
    (string/join "," converted)))
