(ns realworld.burnup.profile.core-test
  (:require [clojure.test :refer :all]
            [realworld.burnup.profile.interface :as profile]))

;; add your tests here...
(deftest test-add-two
  (is (= 42 (profile/add-two 40))))
