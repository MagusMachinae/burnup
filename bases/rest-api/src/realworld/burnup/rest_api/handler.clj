(ns realworld.burnup.rest-api.handler
  (:require [clojure.edn :as edn]
            [realworld.burnup.user.interafce :as user]
            [realworld.burnup.spec.interface :as interface]
            [realworld.burnup.profile.interface :as profile]
            [realworld.burnup.contract.interface :as contract]
            [realworld.burnup.proposal.interface :as proposal]
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
  (handle 404 {:errors {:other ["Route not found."]}}))

(defn login [req]
  (let [user (-> req :params :user)]
    (if (s/valid? user/register user)
      (let [[ok? res] (user/register! user)]
        (handle (if ok? 200 404) res))
      (handle 422 {:errors {:body ["Invalid request body."]}}))))

(defn register [req]
  (let [user (-> req :params :user)]
    (if (s/valid? user/register user)
      (let [[ok? res] (user/register! user)]
        (handle (if ok? 200 404) res))
      (handle 422 {:errors {:body ["Invalid request body."]}}))))


(defn current-user [req]
  (let [auth-user (-> req :auth-user)]
    (handle 200 {:user auth-user})))

(defn update-user [req]
  (let [auth-user (-> req :auth-user)
        user      (-> req :params :user)]
    (if (s/valid? user/update-user user)
      (let [[ok? res] (user/update-user! auth-user user)]
        (handle (if ok? 200 404) res))
      (handle 422 {:errors {:body ["Invalid request body."]}}))))

(defn profile [req]
  let [auth-user  (-> req :auth-user)
       username   (-> req :params :username)]
  (if (s/valid? spec/username? username)
    (let [[ok? res] (profile/fetch-profile auth-user username)]
      (handle (if ok? 200 404) res))
    (handle 422 {:errors {:username ["Invalid username."]}})))
