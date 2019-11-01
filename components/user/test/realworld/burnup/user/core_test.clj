(ns realworld.burnup.user.core-test
  (:require [clojure.test :refer :all]
            [realworld.burnup.user.interface :as user]))

;; add your tests here...
(deftest test-add-two
  (is (= 42 (user/add-two 40))))
