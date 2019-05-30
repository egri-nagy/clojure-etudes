(ns etudes.zebra
  (:gen-class)
  (:use clojure.core.logic))

(require '[clojure.core.logic.fd :as fd])

(defn rule2
  "The Englishman lives in the red house."
  [answers]
  (membero [:englishman :red (lvar) (lvar) (lvar) (lvar)] answers))

(defn rule3
  "The Spaniard owns the dog."
  [answers]
  (membero [:spaniard (lvar) (lvar) :dog (lvar) (lvar)] answers))

(defn rule4
  "Coffee is drunk in the green house."
  [answers]
  (membero [(lvar) :green (lvar) (lvar) (lvar) :coffee] answers))

(defn rule5
  "The Ukrainian drinks tea."
  [answers]
  (membero [:ukrainian (lvar) (lvar) (lvar) (lvar) :tea] answers))

(defn rule6
  "The green house is immediately to the right of the ivory house."
  [answers]
  (fresh [l1 l2]
    (membero l1 [1 2 3 4 5])
    (membero l2 [1 2 3 4 5])
    (membero [(lvar) :green l1 (lvar) (lvar) (lvar)] answers)
    (membero [(lvar) :ivory l2 (lvar) (lvar) (lvar)] answers)
    (fd/- l1 l2 1)))

(defn rule7
  "The Old Gold smoker owns snails."
  [answers]
  (membero [(lvar) (lvar) (lvar) :snails :oldgold (lvar)] answers))

(defn rule8
  "Kools are smoked in the yellow house."
  [answers]
  (membero [(lvar) :yellow (lvar) (lvar) :kool (lvar)] answers))

(defn rule9
  "Milk is drunk in the middle house."
  [answers]
  (membero [(lvar) (lvar) 3 (lvar) (lvar) :milk] answers))

(defn rule10
  "The Norwegian lives in the first house."
  [answers]
  (membero [:norwegian (lvar) 1 (lvar) (lvar) (lvar)] answers))

(defn rule11
  "The man who smokes Chesterfields lives in the house next to the man with the fox."
  [answers]
  (fresh [l1 l2]
    (membero l1 [1 2 3 4 5])
    (membero l2 [1 2 3 4 5])
    (membero [(lvar) (lvar) l1 (lvar) :chesterfield (lvar)] answers)
    (membero [(lvar) (lvar) l2 :fox (lvar) (lvar)] answers)
    (conde [(fd/- l2 l1 1)]
           [(fd/- l1 l2 1)])))

(defn rule12
  "Kools are smoked in the house next to the house where the horse is kept."
   [answers]
  (fresh [l1 l2]
    (membero l1 [1 2 3 4 5])
    (membero l2 [1 2 3 4 5])
    (membero [(lvar) (lvar) l1 (lvar) :kool (lvar)] answers)
    (membero [(lvar) (lvar) l2 :horse (lvar) (lvar)] answers)
    (conde [(fd/- l2 l1 1)]
           [(fd/- l1 l2 1)])))

(defn rule13
  "The Lucky Strike smoker drinks orange juice."
  [answers]
  (membero [(lvar) (lvar) (lvar) (lvar) :lucky :orange] answers))

(defn rule14
  "The Japanese smokes Parliaments."
  [answers]
  (membero [:japanese (lvar) (lvar) (lvar) :parliament (lvar)] answers))

(defn rule15
  "The Norwegian lives next to the blue house."
  [answers]
  (fresh [l1 l2]
    (membero l1 [1 2 3 4 5])
    (membero l2 [1 2 3 4 5])
    (membero [:norwegian (lvar) l1 (lvar) (lvar) (lvar)] answers)
    (membero [(lvar) :blue l2 (lvar) (lvar) (lvar)] answers)
    (conde [(fd/- l2 l1 1)]
           [(fd/- l1 l2 1)])))

(defn solve []
  (let [people (repeatedly 5 lvar)
        house (repeatedly 5 lvar)
        location (repeatedly 5 lvar)
        pet (repeatedly 5 lvar)
        tobacco (repeatedly 5 lvar)
        drinks (repeatedly 5 lvar)
        answers (map list people house location pet tobacco drinks)]
    (run 1 [q]
      (== q answers)
      (== people [:englishman :spaniard :ukrainian :norwegian :japanese])
      (rule2 answers)
      (rule3 answers)
      (rule4 answers)
      (rule5 answers)
      (rule6 answers)
      (rule7 answers)
      (rule8 answers)
      (rule9 answers)
      (rule10 answers)
      (rule11 answers)
      (rule12 answers)
      (rule13 answers)
      (rule14 answers)
      (rule15 answers)
      (permuteo house [:red :green :ivory :yellow :blue])
      (permuteo location [1 2 3 4 5])
      (permuteo pet [:dog :snails :fox :horse :zebra])
      (permuteo tobacco [:oldgold :kool :chesterfield :lucky :parliament])
      (permuteo drinks [:tea :milk :orange :coffee :water]))))
