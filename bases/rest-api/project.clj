(defproject realworld.burnup/rest-api "0.1"
  :description "A rest-api base."
  :dependencies [[realworld.burnup/interfaces "1.0"]
                 [environ "1.1.0"]
                 [org.clojure/clojure "1.9.0"]
                 [compojure "1.6.0"]
                 [org.clojure/data.json "0.2.6"]
                 [ring/ring-jetty-adapter "1.6.3"]
                 [ring/ring-json "0.5.0"]
                 [com.taoensso/timbre "4.10.0"]
                 [ring-logger-timbre "0.7.6"]]
  :aot :all)
