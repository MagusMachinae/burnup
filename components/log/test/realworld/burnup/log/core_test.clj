(ns realworld.burnup.log.core-test
  (:require [clojure.test :refer :all]
            [realworld.burnup.log.interface :as log]))

;; add your tests here...
(deftest test-add-two
  (is (= 42 (log/add-two 40))))
