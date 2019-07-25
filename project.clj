(defproject etudes "0.1"
  :description "coding exercises in Clojure"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/core.logic "0.8.11"]
                 [org.clojure/core.match "0.3.0"]
                 [quil "3.0.0"]
                 [tesser.core "1.0.3"]
                 [criterium "0.4.5"]
                 [com.bhauman/rebel-readline "0.1.4"]
                 [nrepl "0.6.0"]]
  :plugins [[lein-kibit "0.1.7"]
            [lein-ancient "0.6.15"]
            [lein-bikeshed "0.5.2"]
            [jonase/eastwood "0.3.6"]]
  :main ^:skip-aot etudes.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  :aliases {"rebl" ["trampoline" "run" "-m" "rebel-readline.main"]})
