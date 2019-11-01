(ns realworld.burnup.user.interface
  (:require [realworld.burnup.user.core :as core]))

;; delegate to the implementations...
(defn add-two [x]
  (core/add-two x))
