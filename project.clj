(defproject etudes "0.1"
  :description "coding exercises in Clojure"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/core.logic "0.8.11"]
                 [org.clojure/core.match "0.3.0-alpha5"]
                 [quil "2.8.0"]
                 [tesser.core "1.0.3"]
                 [criterium "0.4.4"]
                 [com.bhauman/rebel-readline "0.1.4"]]
  :plugins [[lein-kibit "0.1.2"]
            [lein-ancient "0.6.14"]
            [lein-bikeshed "0.3.0"]
            [jonase/eastwood "0.2.3"]]
  :main ^:skip-aot etudes.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  :aliases {"rebl" ["trampoline" "run" "-m" "rebel-readline.main"]})
