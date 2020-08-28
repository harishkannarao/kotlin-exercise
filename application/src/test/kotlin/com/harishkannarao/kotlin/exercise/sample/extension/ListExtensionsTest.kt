package com.harishkannarao.kotlin.exercise.sample.extension

import com.harishkannarao.kotlin.exercise.sample.extension.ListExtensions.dropFirst
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.testng.annotations.Test

class ListExtensionsTest {
    @Test
    fun `swaps the indices in a list`() {
        val underTest = mutableListOf("one", "two", "three")
        underTest.swap(0, 1)
        assertThat(underTest[0], equalTo("two"))
        assertThat(underTest[1], equalTo("one"))
        assertThat(underTest[2], equalTo("three"))
    }

    @Test
    fun `dropFirst removes first element from a list`() {
        val underTest = listOf("one", "two", "three")
        val result = underTest.dropFirst()
        assertThat(result, contains("two", "three"))
    }

    @Test
    fun `dropFirst returns empty list for input with single element`() {
        val underTest = listOf("one")
        val result = underTest.dropFirst()
        assertThat(result, empty())
    }

    @Test
    fun `dropFirst returns empty list for empty list as input`() {
        val underTest = emptyList<String>()
        val result = underTest.dropFirst()
        assertThat(result, empty())
    }
}
