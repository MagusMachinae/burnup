(ns realworld.burnup.log.interface
  (:require [realworld.burnup.log.core :as core]))

;; delegate to the implementations...
(defn add-two [x]
  (core/add-two x))
