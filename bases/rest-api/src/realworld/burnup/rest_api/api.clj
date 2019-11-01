(ns realworld.burnup.rest-api.api
  (:require [realworld.burnup.graph-database.interface :as database])
  (:require [compojure.core :refer [routes wrap-routes defroutes GET POST PUT DELETE ANY OPTIONS]]
            [ring.logger.timbre :as logger]
            [ring.middleware.json :as js]
            [ring.middleware.keyword-params :as kp]
            [ring.middleware.multipart-params :as mp]
            [ring.middleware.nested-params :as np]
            [ring.middleware.params :as pr]))

;; A stand alone base example. Change to the right type of base.
(defroutes
  (OPTIONS "/**"          []h/options)
  (GET     "/api/health"  []h/health)
  (POST    "/api/users"))

(def ^:private app-routes
  (routes
    (-> private-routes
        (wrap-routes m/wrap-authorization)
        (wrap-routes m/wrap-auth-user))
    (-> public-routes
        (wrap-routes m/wrap-auth-user))
    other-routes))

(def app
  (-> app-routes
      logger/wrap-with-logger
      kp/wrap-keyword-params
      pr/wrap-params
      mp/wrap-multipart-params
      js/wrap-json-params
      np/wrap-nested-params
      m/wrap-exceptions
      js/wrap-json-response
      m/wrap-cors))

(defn init []
  (try
    (log/init)
    (let [db (database/db)]
      (if (database/db-exists?)
        (if (database/valid-schema? db)
          (log/info "Database schema is valid.")
          (do
            (log/warn "Please fix database schema and restart")
            (System/exit 1)))
        (do
          (log/info "Generating database.")
          (database/generate-db db)
          (log/info "Database generated."))))
    (log/info "Initialized server.")
    (catch Exception e
      (log/error e "Could not start server."))))

(defn destroy []
  (log/info "Destroyed server."))
