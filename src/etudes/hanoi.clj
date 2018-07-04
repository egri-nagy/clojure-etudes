(ns etudes.hanoi)

(defn towers-of-hanoi
  [numofdisks source target spare]
   (if (= 1 numofdisks) (str " " source "->" target " ")
       (str
         (towers-of-hanoi (dec numofdisks) source spare target)
         (str " " source "->" target " ")
         (towers-of-hanoi (dec numofdisks) spare target source))))

(towers-of-hanoi 4 \1 \2 \3)
