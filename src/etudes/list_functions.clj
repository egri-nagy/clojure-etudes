(ns etudes.list-functions)

(defn palindrome?
  "Decides whether a sequence is palindrome or not."
  [l]
  (= l (reverse l)))
