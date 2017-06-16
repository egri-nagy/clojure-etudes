(defproject etudes "0.1"
  :description "coding exercises in Clojure"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/core.logic "0.8.11"]
                 [tesser.core "1.0.2"]
                 [criterium "0.4.4"]]
  :plugins [[lein-kibit "0.1.2"]
            [lein-ancient "0.6.10"]
            [lein-bikeshed "0.3.0"]
            [jonase/eastwood "0.2.3"]]
  :main ^:skip-aot etudes.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
