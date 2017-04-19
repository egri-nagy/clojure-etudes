(ns etudes.caesar-shift
  "Caesar shift and its brute-force attack.")

(def letters "abcdefghijklmnopqrstuvwxyz ")

(defn shift-map
  "A hash-map that maps letters to letters cyclically shifted by n."
  [n]
  (zipmap letters
          (take (count letters)
                (drop n (cycle letters)))))

(defn encrypter
  "A function the encrypts plaintext by an n-shift cipher."
  [n]
  (fn [text]
    (apply str
           (map (shift-map n) text))))

(defn decrypter
  "A decrypter is just an encrypter that shifts back."
  [n]
  (encrypter (mod (- n) (count letters))))

(defn brute-force-attack
  "Calculates all shifts and thus breaking the cipher."
  [ciphertext]
  (map
   (fn [n] ((decrypter n) ciphertext))
   (range 1 (count letters))))
