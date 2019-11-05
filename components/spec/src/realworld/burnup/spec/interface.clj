(ns realworld.burnup.spec.interface
  (:require [realworld.burnup.spec.core :as core]))

;; delegate to the implementations...
(defn add-two [x]
  (core/add-two x))
