(ns etudes.hanoi)

(defn towers-of-hanoi
  ([numofdisks] (towers-of-hanoi numofdisks :rodA :rodB :rodC))
  ([numofdisks from to via]
   (if (= 1 numofdisks) (println (format "%s to %s" from to))
       (do
         (towers-of-hanoi (dec numofdisks) from via to)
         (println (format "%s to %s" from to))
         (recur (dec numofdisks) via to from)))))

(towers-of-hanoi 3)
