(ns vm-clojure.vm)

(def NICKEL 5)
(def DIME 10)
(def QUARTER 25)

(defn do-make-change [change-due, coins]
  (if (and (not-empty coins) (> change-due 0)) 
  (let [coin (first coins)
        rest (rest coins)]
    (if (>= change-due coin)
      (cons coin (do-make-change (- change-due coin) rest))
      (do-make-change change-due rest)))
  []))

(defn make-change [change-due, coins]
  (let [sorted (reverse (sort coins))]
    (do-make-change change-due sorted)))

(defn change-due [purchase-price, coins]
  (- (reduce + coins) purchase-price))

(defn sufficient-funds
  "Are there sufficient funds for the purchase?"
  [purchase-price, coins] 
  (<= purchase-price (reduce + coins)))

(defn rm-one
  "Remove at most one of 'x' from the collection."
  [x coll]
  (let [[head & rest] coll]
    (if (= x head)
      rest
      (cons head (rm-one x rest)))))

(defn rm-these
  "Remove at most one of each of the x's from the collection."
  [xs coll]
  (if (empty? xs)
    coll
    (rm-these (rest xs) (rm-one (first xs) coll))))

(defn purchase 
  "Given a purchase-price (cents), a sequence of deposited coins, and
   a sequence of reserve coins in the vending machine, return a map
   indicating success/failure, change (if applicable), and remaining
   reserve."
  [purchase-price, deposits, reserve]
  (cond (sufficient-funds purchase-price deposits)
        (let [change (change-due purchase-price deposits)
              reserve-plus-deposited (concat deposits reserve)
              change-coins (make-change change reserve-plus-deposited)
              new-reserve (rm-these change-coins reserve-plus-deposited)]
          {:success true :change change-coins :reserve new-reserve}
          )
        :else {:success false :reserve reserve}
        ))
