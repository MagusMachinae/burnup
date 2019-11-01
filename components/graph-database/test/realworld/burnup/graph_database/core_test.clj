(ns realworld.burnup.graph-database.core-test
  (:require [clojure.test :refer :all]
            [realworld.burnup.graph-database.interface :as graph-database]))

;; add your tests here...
(deftest test-add-two
  (is (= 42 (graph-database/add-two 40))))
