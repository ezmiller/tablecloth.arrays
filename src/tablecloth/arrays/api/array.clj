(ns tablecloth.arrays.api.array
  (:require [tech.v3.datatype :as dtype]))

(defn array [data datatype]
  (dtype/->reader data datatype))

(defn is-array [item]
  (dtype/reader? item))

(defn array-is [item container-type]
  (= container-type
     (dtype/datatype item)))

(defn contains [item datatype]
  (= datatype
     (dtype/elemwise-datatype item)))

(comment
  (require '[tech.v3.datatype.datetime :as datetime])

  (require '[tech.v3.datatype.functional :as fun])

  (require '[tech.v3.tensor :as tensor])


  (defn timeit [f]
    (let [start-time  (datetime/instant)
          result (f)
          end-time (datetime/instant)]
      (str (datetime/between start-time end-time :milliseconds) "ms")))


  (def tobuffer (dtype/->buffer [1 2 3]))
  (def toreader (dtype/->reader [1 2 3]))

  (dtype/datatype tobuffer);; => :buffer
  (dtype/datatype toreader);; => :buffer


  (def ary (array [1.0 2 3] :int8))

  (is-array ary)
  (array-is ary :reader) 
  (contains ary :int8)

  (dtype/reader? (array [1 2 3] :int8))

  (dtype/reader-like? (array [1 2 3] :int8))

  (def a-reader (dtype/->reader [1 2 3] :int8))

  (dtype/datatype a-reader)
  ;; => :buffer

  (dtype/reader? a-reader)
  ;; => true

  (dtype/reader-like? a-reader)
  ;; => false

  (dtype/writer? a-reader);; => true

  (dtype/writer? (dtype/clone a-reader))

  (def computed-reader (dtype/make-reader :int8 10 (* 10 idx)))

  computed-reader

  (dtype/writer? computed-reader)

  (dtype/writer? (dtype/clone computed-reader))

  (dtype/datatype computed-reader)

  )

