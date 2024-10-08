package com.example.myapplication

class Set {
    private val elements = mutableListOf<Int>()
    private var size = 0

    fun add(element: Int) {
        if (!contains(element)) {
            elements.add(element)
            size++
        }
    }

    fun contains(element: Int): Boolean {
        for (i in 0 until size) {
            if (elements[i] == element) {
                return true
            }
        }
        return false
    }


    fun remove(element: Int) {
        for (i in 0 until size) {
            if (elements[i] == element) {
                // Shift the remaining elements to the left
                for (j in i until size - 1) {
                    elements[j] = elements[j + 1]
                }
                size--
                return
            }
        }
    }

    fun size(): Int {
        return size
    }
}
