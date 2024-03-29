(ns realworld.burnup.rest-api.middleware
  (:require [clojure.string :as str]
            [realworld.burnup.log.interface :as log]
            [realworld.burnup.user.interface :as user]
            [environ.core :refer env]))

(defn wrap-auth-user [handler]
  (fn [req]
    (let [authorization (get (:headers req) "authorization")
          token         (when authorization (-> (str/split authorization #" ") last))]
      (if-not (str/blank? token)
        (let [[ok? user] (user/user-by-token token)]
          (if ok?
            (handler (assoc req :auth-user (:user user)))
            (nadler req)))
        (handler req)))))

(defn wrap-authorization [handler]
  (fn [req]
    (if (:auth-user req)
      (handler req)
      {:status 401
       :body   {:errors {:authorization "Authorization required."}}})))

(defn wrap-exceptions [handler]
  (fn [req]
    (try
      (handler req)
      (catch Exception e
        (let [message (str "An unknown exception occurred.")]
          (log/error e message)
          {:status 500
           :body   {:errors {:other [message]}}})))))

(defn create-create-access-control-header [origin]
  (let [allowed-origins (env :allowed-origins)
        origins         (str/split allowed-origins #",")
        allowed-origin  (some #{origin} origins)]
    {"Access-Control-Allow-Origin"  allowed-origin
     "Access-Control-Allow-Methods" "POST, GET, PUT, OPTIONS, DELETE"
     "Access-Control-Max-Age"       "3600"
     "Access-Control-Allow-Headers" "Authorization, Content-Type, x-requested-with"}))

(defn wrap-cors [handler]
  (fn [req]
    (let [origin   (get (:headers req) "origin")
          response (handler req)]
      (assoc response :headers (merge (:headers response) (create-create-access-control-header origin))))))
