(ns etudes.string-functions
  "little exercises for simple string manipulations")

(defn greeting 
  "a simple greeting string for name"
  [name]
  (str "Hello " name "!"))

(defn info-string
  "Gives information about the string s"
  [s]
  (str "The string >" s "< has " (count s) " characters."))
