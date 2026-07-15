(ns association.facts-test
  (:require [clojure.test :refer [deftest is]]
            [association.facts :as facts]))

(deftest awwa-has-spec-basis
  (let [sb (facts/spec-basis "awwa")]
    (is (= 2 (count sb)))
    (is (every? #(= "3600" (:association-rule/isic %)) sb))
    (is (every? #(= "USA" (:association-rule/country %)) sb))))

(deftest unknown-association-has-no-spec-basis
  (is (nil? (facts/spec-basis "wef")))
  (is (nil? (facts/spec-basis "zzz"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["awwa" "wef"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["wef"] (:missing-associations c)))))

(deftest by-topic-filters
  (is (= ["awwa.policy-distribution-system-water-quality"]
         (mapv :association-rule/id (facts/by-topic "awwa" :water-quality))))
  (is (empty? (facts/by-topic "awwa" :labor)))
  (is (empty? (facts/by-topic "wef" :governance))))
