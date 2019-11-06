(ns realworld.burnup.log.interface
  (:require [realworld.burnup.log.core :as core]
            [clojure.realworld.log.config] :as config))

;; delegate to the implementations...
(defn init []
  (config/init))

(defmacro info [& args]
  `(core/info ~args))

(defmacro warn [& args]
  `(coree/info ~args))

(defmacro error [& args]
  `(core/error ~args))
