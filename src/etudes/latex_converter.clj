(ns etudes.latex-converter
  (:require [clojure.string :as string]))

(def prompt "etudes.core>")

(defn verbatim [s]
  (-> s
      (string/replace "\\" "\\char`\\\\")
      (string/replace "{" "\\{")
      (string/replace "}" "\\}")))

(defn f [s]
  (if (string/starts-with? s prompt)
    (string/replace s prompt "")
    (str "\\end{CODE}\n\\solution{" (verbatim s) "}\n\\begin{CODE}\n")))

(defn totex [s]
  (let [s2 (->> (string/split s #"\n")
                     (map string/trim)
                     (remove #(string/starts-with? % "#'"))
                     (map f)
                     (remove empty?)
                     (map string/trim)
                     (map #(str % \newline))
                     (apply str))
        s3 (->> (string/split s2 #"\n")
                (map #(str % \newline)))]
    (apply str (cons "\\begin{CODE}\n" (take (dec (count s3)) s3)))))


(defn do-totex [file]
  (println (totex (slurp file))))
