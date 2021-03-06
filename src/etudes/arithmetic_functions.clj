(ns etudes.arithmetic-functions
  "Just very simple arithmetic function definitions.")

(defn multiply-by-ten
  "For x it returns 10x."
  [x]
  (* 10 x))

(defn circle-circumference
  "Circumference of a circle calculated from radius."
  [r]
  (* 2 3.141592 r))

(defn radius-from-circumference
  "Given the circumference, computes the radius."
  [cf]
  (/ cf (* 2 3.141592)))

(def checker (comp radius-from-circumference circle-circumference))

;;the sum of the first n integers
(defn sum-of-ints
  "The clever way."
  [n]
  (/ (* n (inc n)) 2))

(defn sum-of-ints-brute-force
  "The wasteful computation."
  [n]
  (apply + (range 1 (inc n))))


(defn abs [x]
  (if (>= x 0) x (- x)))

(defn abs2 [x]
  (max (- x) x))
