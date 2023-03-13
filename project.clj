(defproject etudes "0.1"
  :description "coding exercises in Clojure"
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [org.clojure/core.logic "1.0.1"]
                 [org.clojure/core.match "1.0.1"]
                 [quil "3.1.0"]
                 [tesser.core "1.0.6"]
                 [criterium "0.4.6"]
                 [com.bhauman/rebel-readline "0.1.4"]
                 [meander/epsilon "0.0.650"]
                 [nrepl "1.0.0"]]
  :plugins [[lein-kibit "0.1.8"]
            [lein-ancient "0.7.0"]
            [lein-bikeshed "0.5.2"]
            [jonase/eastwood "1.3.0"]]
  :main ^:skip-aot etudes.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  :aliases {"rebl" ["trampoline" "run" "-m" "rebel-readline.main"]})
