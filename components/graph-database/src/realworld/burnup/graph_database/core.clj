(ns realworld.burnup.graph-database.core
  (:import datomic.Util)
  (:require [clojure.java.io :as io]
            [environ.core :refer env]))


;; add your functions here...
(defn- db-path []
  (if (contains? env :database)
    (env :database)
    "database.edn"))

(defn- scratch-conn)

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

((defn db
  [path]
  {}))
