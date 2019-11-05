(ns realworld.burnup.contract.interface
  (:require [realworld.burnup.contract.core :as core]))

;; delegate to the implementations...
(defn add-two [x]
  (core/add-two x))
