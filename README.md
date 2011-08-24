# clojure vending machine

## What is it

Partial Vending Machine implementation in Clojure according to the Lambda
Lounge Language Shootout vending machine specification
http://lambdalounge.org/

## Running

Launch repl with project in classpath, i.e:

`java -cp clojure.jar:vending-machine/src clojure.main`

Then load in repl, i.e:

`(use 'vm-clojure.vm)`

and

`(purchase 55 [QUARTER QUARTER DIME] [DIME NICKEL QUARTER NICKEL])`
