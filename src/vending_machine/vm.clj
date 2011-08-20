(ns vending-machine.vm)

(def NICKEL 5)
(def DIME 10)
(def QUARTER 25)

(defn coin-return [] (list NICKEL DIME))

(defn purchase [purchase-price, deposits, reserve]
  "Indicate true/false if purchase succeeded, given the int (cents) purchase
   price, sequence of deposited coins, and sequence of available reserve
   coins."

  (cond (sufficient-funds purchase-price deposits)
        (let [change (change-due purchase-price deposits)
              reserve-plus-deposited (concat deposits reserve)
              change-coins (make-change change reserve-plus-deposited)
              new-reserve (remove #(some #{%} change-coins) reserve-plus-deposited)]
          {:success true :change change-coins :reserve new-reserve}
          )
        :else {:success false :reserve reserve}
        ))

(defn sufficient-funds [purchase-price, coins]
  (<= purchase-price (reduce + coins)))

(defn change-due [purchase-price, coins]
  (- (reduce + coins) purchase-price))

(defn make-change [change-due, coins]
  [NICKEL]
  )
