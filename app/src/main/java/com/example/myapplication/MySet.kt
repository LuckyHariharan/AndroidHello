package com.example.myapplication

class MySet {
    // Step 0, initialize a list of length 5
    // initialize a counter with 0
    var list = MutableList(5) { 0 }
    var counter = 0

    // Step 1 create isEmpty, add, and contain methods
    fun isEmpty(): Boolean {
        return if(counter == 0){
            true
        } else {
            false
        }
    }

    // Create add method --> Pseudocde
    fun add(element: Int) {

    }

    // contains method

    // remove method

}