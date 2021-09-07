(ns tablecloth.arrays.api.array
  (:require [tech.v3.datatype :as dtype]
            [tech.v3.tensor :as tensor]))

(defn array
  ([data]
   (array data :object))
  ([data datatype]
   (let [shape (dtype/shape data)]
     (if (> (count shape) 1)
       (tensor/->tensor data :datatype datatype)
       (dtype/->reader data datatype)))))

(defmacro compute-array
  ([shape read-op]
   `(compute-array ~shape ~read-op :object))
  ([shape read-op datatype]
   (if (> (count shape) 1)
     `(tensor/compute-tensor ~shape ~read-op ~datatype)
     `(dtype/make-reader ~datatype ~(first shape) ~read-op))))

(defmacro zeros
  ([shape]
   `(zeros ~shape :int))
  ([shape datatype]
   `(compute-array ~shape
                   (if (> (count ~shape) 1)
                     (constantly 0)
                     0)
                   ~datatype)))

(defmacro ones
  ([shape]
   `(zeros ~shape :int))
  ([shape datatype]
   `(compute-array ~shape
                   (if (> (count ~shape) 1)
                     (constantly 1)
                     1)
                   ~datatype)))

(defn is-array [item]
  (= :buffer
     (dtype/datatype item)))

(defn array-is [item container-type]
  (= container-type
     (dtype/datatype item)))

(defn contains?
  ([item]
   (dtype/elemwise-datatype item))
  ([item datatype]
   (= datatype
      (dtype/elemwise-datatype item))))

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


  (def ary (array [1 2 3] :int8))

  (type ary);; => tech.v3.datatype.base$eval10951$fn__10980$fn$reify__10989

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


  (dtype/datatype (dtype/clone a-reader));; => :array-buffer
  
  (dtype/datatype [1 2 3])



  )

