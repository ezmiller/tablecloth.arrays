(ns tablecloth.arrays.array-test
  (:require [tablecloth.arrays.api.array :refer [array
                                                 is-array
                                                 array-is]]
            [tech.v3.datatype :as dtype]
            [clojure.test :refer [is deftest testing]]))


(deftest array-test
  (testing "properties of returned 1d collection"
    (is (= :buffer
           (dtype/datatype (array [1 2 3] :int8))))
    (is (= true
           (dtype/reader?
            (array [1 2 3] :int8)))))
  (testing "properties of returned nd collection"
    (is (= :tensor
           (dtype/datatype (array [[1 2]
                                   [3 4]]
                                  :int8))))))

(deftest is-array-test
  (is (= true
         (is-array (array [1 2 3] :int8))))
  (is (= false
         (is-array [1 2 3]))))


(deftest array-is-test
  (testing "detection of :buffer"
    (is (= true
          (-> (array [1 2 3] :int8)
              (array-is :buffer))))
    (is (= false
          (-> [1 2 3]
              (array-is :buffer)))))
  (testing "detection of :array-buffer"
    (is (= false
           (-> (array [1 2 3] :int8)
               (array-is :array-buffer))))
    (is (= true
           (-> (array [1 2 3] :int8)
               (dtype/make-container)
               (array-is :array-buffer))))))

