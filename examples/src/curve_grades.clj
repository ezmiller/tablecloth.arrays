(ns curve-grades)

(require '[tablecloth.arrays.api.array :refer [array] :as arrays]
         '[tech.v3.datatype.functional :as fun]
         '[tech.v3.datatype :as dtype])


;; https://realpython.com/numpy-tutorial/#hello-numpy-curving-test-grades-tutorial

;; >>> grades = np.array([72, 35, 64, 88, 51, 90, 74, 12])
(def grades (array [72 35 64 88 51 90 74 12] :int32))

;; >>> def curve(grades):
;; ...     average = grades.mean()
;; ...     change = CURVE_CENTER - average
;; ...     new_grades = grades + change
;; ...     return np.clip(new_grades, grades, 100)
(let [average (fun/mean grades)
      curve-center 80
      delta (dtype/emap #(- % average) :int32 grades)]
  (dtype/emap #(min % 100) ;; clipping
              :int32
              (fun/+ grades delta)))
