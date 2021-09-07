(ns numpy-quickstart)

(require '[tablecloth.arrays.api.array :refer [array] :as ary])

;; Array creation

;; a = np.array([2, 3, 4])
(def a (array [2 3 4] :int8))

;; a.dtype 
(ary/contains? a)

(ary/contains? a :int8)

;; Array creation with values

;; np.zeros(10, dtype=int)
(ary/zeros [10] :int)

;; np.ones(2)
(ary/ones [2] :int)

;; np.empty(3) => creates random elements that depend on state of memory
nil

;; >>> np.arange(10, 30, 5)
;; array([10, 15, 20, 25])
(array (range 10 30 5) :int)

;; >>> np.arange(0, 2, 0.3)  # it accepts float arguments
;; array([0. , 0.3, 0.6, 0.9, 1.2, 1.5, 1.8])
(array (range 0 2 0.3) :float)

;;linspace?
nil

;; Creating tensors ;;

(-> [[1 2 3]
     [4 5 6]]
    (array :int))

(-> [[:a :b :c]
     [:d :e :f]]
    (array :keyword))
   

;; Basic Operations ;;
(def a (-> [20 30 40 50] (array :int)))
(def b (-> (range 4) (array :int)))


