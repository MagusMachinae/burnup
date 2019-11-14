(ns realworld.burnup.graph-database.core
  (:import datomic.Util)
  (:require [clojure.java.io :as io]
            [environ.core :refer env]
            [datomic.api :as d]))


;; add your functions here...
(defn- db-path []
  (if (contains? env :database)
    (env :database)
    "database.edn"))

(def db-uri-base "datomic:mem://")

(defn- scratch-conn
  "Create a connection to an anonymous, in memory
database"
  []
  (let [uri (str db-uri-base (d/squuid))]
    (d/delete-database uri)
    (d/create-database uri)
    (d/connect uri)))

(defn read-all
  "Read all forms in f, where f is any resourced that
can be opened by io/reader"
  [f]
  (Util/readAll (io/reader f)))

(defn transact-all
  "Load and run all transactions from f, where f is
any resource that can be opened by io/reader"
  [conn f]
  (loop [n 0
         [tx & more] (read-all f)]
    (if tx
      (recur (+ n (count (:tx-data @(d/transact conn tx))))
             more)
      {:datoms n})))

(defn load-data
 [])

(defn db
  ([path]
   {})
  (db (db-path)))

(defn db-exists? []
  (let [db-file (io/file "database.edn")]))
