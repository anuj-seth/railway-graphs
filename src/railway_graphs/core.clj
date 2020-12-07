(ns railway-graphs.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))


{:from :A :to :B :distance 5}
{:from :B :to :C :distance 4} 
{:from :C :to :D :distance 8}
{:from :D :to :E :distance 6}
{:from :E :to :B :distance 3}
{:from :A :to :D :distance 5}
{:from :A :to :E :distance 7}
{:from :D :to :C :distance 8}
{:from :C :to :E :distance 2}
