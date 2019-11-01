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
(defn -main [& args]
  (println "Hello world!"))
