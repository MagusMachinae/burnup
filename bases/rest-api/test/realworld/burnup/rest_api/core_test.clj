(ns realworld.burnup.rest-api.core-test
  (:require [clojure.test :refer :all]
            [realworld.burnup.rest-api.core :as core]))

;; Add tests here...
(deftest hello-world-example-test
  (let [output (with-out-str (core/-main))]
    (is (= "Hello world!\n"
           output))))
