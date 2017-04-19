(ns etudes.caesar-shift)

(def letters "abcdefghijklmnopqrstuvwxyz ")

(defn shift-map
  [n]
  (zipmap letters
          (take (count letters)
                (drop n (cycle letters)))))

(defn encrypter [n]
  (fn [text] (apply str (map (shift-map n) text))))

(defn decrypter [n]
  (encrypter (mod (- n) (count letters))))

(defn brute-force-attack
  [ciphertext]
  (map
   (fn [n] ((decrypter n) ciphertext))
   (range 1 (count letters))))
