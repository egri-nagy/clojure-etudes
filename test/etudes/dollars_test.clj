(ns etudes.dollars-test
  (:require [clojure.test :refer :all]
            [etudes.dollars :refer [dollars]]))

(deftest dollars-test
  (testing "Testing the dollars function."
    (is (= (dollars "")
           "zero dollars and zero cents"))
    (is (= (dollars "0")
           "zero dollars and zero cents"))
    (is (= (dollars "1")
           "one dollar and zero cents"))
    (is (= (dollars "0.1")
           "zero dollars and ten cents"))
    (is (= (dollars "1.")
           "one dollar and zero cents"))
    (is (= (dollars "0.")
           "zero dollars and zero cents"))
    (is (= (dollars ".34")
           "zero dollars and thirty-four cents"))
    (is (= (dollars "0.3456789")
           "zero dollars and thirty-four cents"))
    (is (= (dollars "1.0")
           "one dollar and zero cents"))
    (is (= (dollars "1.01")
           "one dollar and one cent"))
    (is (= (dollars "1000456.13")
           "one million four hundred and fifty-six dollars and thirteen cents"))))
