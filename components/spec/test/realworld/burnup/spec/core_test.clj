(ns realworld.burnup.spec.core-test
  (:require [clojure.test :refer :all]
            [realworld.burnup.spec.interface :as spec]))

;; add your tests here...
(deftest test-add-two
  (is (= 42 (spec/add-two 40))))
