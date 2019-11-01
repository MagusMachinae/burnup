(ns realworld.burnup.graph-database.interface
  (:require [realworld.burnup.graph-database.core :as core]))

;; delegate to the implementations...
(defn add-two [x]
  (core/add-two x))
