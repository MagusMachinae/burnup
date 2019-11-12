(ns realworld.burnup.proposal.interface
  (:require [realworld.burnup.proposal.core :as core]))

;; delegate to the implementations...
(defn add-two [x]
  (core/add-two x))
