(ns etudes.mandelbrot
  (:require [quil.core :as q]))

;; complex number calculations
(defrecord ComplexNumber [re im])

(defn square
  "Squaring a complex number."
  [{a :re b :im}]
  (->ComplexNumber (- (* a a) (* b b))
                   (* 2 a b)))

(defn add
  "The sum of two complex numbers."
  [{a :re b :im} {c :re d :im}]
  (->ComplexNumber (+ a c) (+ b d)))

(defn modulus
  "The modulus of a complex number."
  [{a :re b :im}]
  (Math/sqrt (+ (* a a) (* b b))))

;; defining the Mandelbrot set
(defn outside?
  "Is the complex number c outside the radius two circle?"
  [c]
  (> (modulus c) 2.0))

(defn escape?
  "Does the complex number give rise to an escaping iterated sequence
  of maximum n steps starting from the origin."
  [c n]
  (letfn [(P_c [z] (add (square z) c))
          (iter [z i]
            (cond (zero? i) (outside? z)
                  (outside? z) true
                  :else (iter (P_c z) (dec i))))]
    (iter (->ComplexNumber 0.0 0.0) n)))

(defn mandelbrot-set-view
  [lx ly rx ry xres yres]
  (let [xdiff (/ (- rx lx) xres)
        ydiff (/ (- ry ly) yres)]
    (for [y (range yres)
          x (range xres)
          :when (not (escape? (->ComplexNumber (+ lx (* x xdiff))
                                               (+ ly (* y ydiff)))
                              100))]
      [x y])))

;;Quil sketch part
(defn setup []
  (q/background (q/color 255 255 255 0))
  (q/stroke (q/color 0 0 0 0))
  (doseq [[x y]  (mandelbrot-set-view -2.1 -1.1 1.1 1.1 (q/width) (q/height))] 
    (q/point x y)))
  
(defn draw []
  (q/no-loop))

(q/defsketch example
  :title "Mandelbrot Set"
  :settings #(q/smooth 0)
  :setup setup
  :draw draw
  :size [900 600])        
