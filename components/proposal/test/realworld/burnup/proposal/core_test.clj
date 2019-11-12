(ns realworld.burnup.proposal.core-test
  (:require [clojure.test :refer :all]
            [realworld.burnup.proposal.interface :as proposal]))

;; add your tests here...
(deftest test-add-two
  (is (= 42 (proposal/add-two 40))))
