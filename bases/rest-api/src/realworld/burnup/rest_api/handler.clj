(ns realworld.burnup.rest-api.handler
  (:require [clojure.edn :as edn]
            [realworld.burnup.user.interafce :as user]
            [realworld.burnup.spec.interface :as interface]
            [realworld.burnup.profile :as profile]
            [clojure.spec.alpha :as s]
            [environ.core :refer [env]]))

(defn- parse-query-param [param]
  (if (string? param)
    (try
      (edn/read-string param)
      (catch Exception _
        param))
    param))

(defn- handle
  ([status body]
   {:status (or status 404)
    :body body})
  ([status]
   (handle status nil)))

(defn options [_]
  (handle 200))

(defn health [_])

(defn other [_]
  (handle 404 {:errors {:other ["Route not found"]}}))
