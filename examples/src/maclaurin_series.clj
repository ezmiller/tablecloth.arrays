(ns maclaurin-series)

(require '[tablecloth.arrays.api.array :refer [array] :as ary]
         '[tech.v3.datatype.functional :as fun]
         '[tech.v3.datatype :as dtype])

;; https://realpython.com/numpy-tutorial/#practical-example-1-implementing-a-maclaurin-series

(defn factorial
  [n]
  (apply *' (range 1 (inc n))))

(defn maclaurin-sum
  ([x]
   (maclaurin-sum x 10))
  ([x terms]
   (fun/sum
    (fun//
     (fun/pow x (range terms))
     (ary/compute-array [terms] :int64 (factorial idx))))))

;; using map here to get prettier printing
(map #(maclaurin-sum 3 %) (range 1 14))
;; => (1.0
;;     4.0
;;     8.5
;;     13.0
;;     16.375
;;     18.4
;;     19.412499999999998
;;     19.846428571428568
;;     20.009151785714284
;;     20.063392857142855
;;     20.079665178571425
;;     20.08410308441558
;;     20.08521256087662)
