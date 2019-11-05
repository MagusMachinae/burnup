(ns realworld.burnup.contract.core-test
  (:require [clojure.test :refer :all]
            [realworld.burnup.contract.interface :as contract]))

;; add your tests here...
(deftest test-add-two
  (is (= 42 (contract/add-two 40))))
