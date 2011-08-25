(ns vm-clojure.test.vm
  (:use [vm-clojure.vm])
  (:use [clojure.test]))


(deftest happy-path
  (is (= {:success true, :change [5 10], :reserve [ 25 25 25 25 25]}
         (purchase 60 [QUARTER QUARTER QUARTER] {:reserve [DIME QUARTER
                                                           NICKEL QUARTER]}))))
(deftest wrap-state
  (let [initial-state (new-vending-machine [5 5 10 10])
        result (buy initial-state 60 [25 25 25])]
    (is (= [5 10] result))
    (is (= [25 25 25 5 10] (:reserve @initial-state)))))

(deftest test-mutate
  (let [initial-state (new-vending-machine [5 5 10])]
    (mutate initial-state purchase 60 [25 25 25])
    (is (= {:success true, :change [5 10], :reserve [25 25 25 5]} @initial-state ))))
