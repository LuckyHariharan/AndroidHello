package com.example.myapplication

import org.junit.Assert.*
import org.junit.Test

class SetUnitTest {

    @Test
    fun testAddElement() {
        val set = Set()
        set.add(5)
        assertTrue(set.contains(5))
    }

    @Test
    fun testAddDuplicateElement() {
        val set = Set()
        set.add(5)
        set.add(5)  // duplicate element
        assertEquals(1, set.size())
    }

    @Test
    fun testRemoveElement() {
        val set = Set()
        set.add(5)
        set.remove(5)
        assertFalse(set.contains(5))
    }

    @Test
    fun testRemoveNonExistentElement() {
        val set = Set()
        set.add(5)
        set.remove(10)
        assertEquals(1, set.size())
    }

    @Test
    fun testSetSize() {
        val set = Set()
        set.add(1)
        set.add(2)
        set.add(3)
        assertEquals(3, set.size())
    }
}
