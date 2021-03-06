(ns railway-graphs.core
  (:require [rhizome.viz :as viz])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(def full-routes
  [{:train-no 1 :origin-seq 1 :from :A :to :B :destination-seq 2 :distance 5}
   {:train-no 1 :origin-seq 2 :from :B :to :C :destination-seq 3 :distance 4} 
   {:train-no 1 :origin-seq 3 :from :C :to :D :destination-seq 4 :distance 8}
   {:train-no 2 :origin-seq 1 :from :D :to :E :destination-seq 2 :distance 6}
   {:train-no 2 :origin-seq 2 :from :E :to :B :destination-seq 3 :distance 3}
   {:train-no 3 :origin-seq 1 :from :A :to :D :destination-seq 2 :distance 5}
   {:train-no 4 :origin-seq 1 :from :A :to :E :destination-seq 2 :distance 7}
   {:train-no 5 :origin-seq 1 :from :D :to :C :destination-seq 2 :distance 8}
   {:train-no 6 :origin-seq 1 :from :C :to :E :destination-seq 2 :distance 2}])

(def adjacency-list {:A [{:train-no 1 :node :B :distance 5}
                         {:train-no 3 :node :D :distance 5}
                         {:train-no 4 :node :E :distance 7}]
                     :B [{:train-no 1 :node :C :distance 4}]
                     :C [{:train-no 1 :node :D :distance 8}
                         {:train-no 6 :node :E :distance 2}]
                     :D [{:train-no 2 :node :E :distance 6}
                         {:train-no 5 :node :C :distance 8}]
                     :E [{:train-no 2 :node :B :distance 3}]})

(def adjacency-list {:A {:B 5
                         :D 5
                         :E 7}
                     :B {:C 4}
                     :C {:D 8
                         :E 2}
                     :D {:E 6
                         :C 8}
                     :E {:B 3}})

(viz/view-graph (keys adjacency-list)
                (fn [n] (keys (adjacency-list n)))
                :vertical? false
                :node->descriptor (fn [n] {:label n})
                :edge->descriptor (fn [from to] {:label ((adjacency-list from) to)}))

;; The distance of the route A-B-C. Ans: 9
;; The distance of the route A-D. Ans: 5
;; The distance of the route A-D-C. Ans: 13
;; The distance of the route A-E-B-C-D. Ans: 22
;; The distance of the route A-E-D. Ans: No such route
;; The number of trips starting at C and ending at C with a maximum of 3 stops. In the sample data below, there are two such trips: C-D-C (2 stops). and C-E-B-C (3 stops).
;; The number of trips starting at A and ending at C with exactly 4 stops. In the sample data below, there are three such trips: A to C (via B,C,D); A to C (via D,C,D); and A to C (via D,E,B).
;; The length of the shortest route (in terms of distance to travel) from A to C. Ans: 9
;; The length of the shortest route (in terms of distance to travel) from B to B.  Ans: 9 (how?) B-C-E-B
;; The number of different routes from C to C with a distance of less than 30. In the sample data, the trips are: CDC, CEBC, CEBCDC, CDCEBC, CDEBC, CEBCEBC, CEBCEBCEBC.

