(ns realworld.burnup.profile.interface
  (:require [realworld.burnup.profile.core :as core]))

;; delegate to the implementations...
(defn add-two [x]
  (core/add-two x))
