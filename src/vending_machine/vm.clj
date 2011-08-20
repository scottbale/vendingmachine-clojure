(ns vending-machine.vm)

(def NICKEL 5)
(def DIME 10)
(def QUARTER 25)

(defn coin-return [] (list NICKEL DIME))

(defn purchase [purchase-price, deposits, coin-return]
  "Indicate true/false if purchase succeeded, given the int (cents) purchase
   price, sequence of deposited coins, and sequence of available coin return
   coins."

  (cond (sufficient-funds purchase-price deposits)
        {:success true :change (change-due purchase-price deposits) :reserve deposits}
  :else {:success false}
        ))

(defn sufficient-funds [purchase-price, coins]
  (<= purchase-price (reduce + coins)))

(defn change-due [purchase-price, coins]
  (- (reduce + coins) purchase-price))

(defn make-change [change-due, coins]
  0
  )
