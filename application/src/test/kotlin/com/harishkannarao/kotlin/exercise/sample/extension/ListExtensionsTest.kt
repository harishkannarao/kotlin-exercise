package com.harishkannarao.kotlin.exercise.sample.extension

import com.harishkannarao.kotlin.exercise.sample.extension.ListExtensions.dropFirst
import org.amshove.kluent.shouldBeEmpty
import org.amshove.kluent.shouldBeEqualTo
import org.testng.annotations.Test

class ListExtensionsTest {
    @Test
    fun `swaps the indices in a list`() {
        val underTest = mutableListOf("one", "two", "three")
        underTest.swap(0, 1)
        underTest[0].shouldBeEqualTo("two")
        underTest[1].shouldBeEqualTo("one")
        underTest[2].shouldBeEqualTo("three")
    }

    @Test
    fun `dropFirst removes first element from a list`() {
        val underTest = listOf("one", "two", "three")
        val result = underTest.dropFirst()
        result.shouldBeEqualTo(listOf("two", "three"))
    }

    @Test
    fun `dropFirst returns empty list for input with single element`() {
        val underTest = listOf("one")
        val result = underTest.dropFirst()
        result.shouldBeEmpty()
    }

    @Test
    fun `dropFirst returns empty list for empty list as input`() {
        val underTest = emptyList<String>()
        val result = underTest.dropFirst()
        result.shouldBeEmpty()
    }
}
