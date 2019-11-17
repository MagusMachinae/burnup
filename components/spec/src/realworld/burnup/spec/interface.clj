(ns realworld.burnup.spec.interface
  (:require [realworld.burnup.spec.core :as core]))

;; delegate to the implementations...
(def username? core/username?)

(def non-empty-string? core/non-empty-string?)

(def email? core/email?)

(def uri-string? core/uri-string?)

(def password? core/password?)
