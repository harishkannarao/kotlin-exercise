package com.harishkannarao.kotlin.exercise.sample.extension

import assertk.assertThat
import assertk.assertions.containsExactlyInAnyOrder
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import com.harishkannarao.kotlin.exercise.sample.extension.ListExtensions.dropFirst
import org.testng.annotations.Test

class ListExtensionsTest {
    @Test
    fun `swaps the indices in a list`() {
        val underTest = mutableListOf("one", "two", "three")
        underTest.swap(0, 1)
        assertThat(underTest[0]).isEqualTo("two")
        assertThat(underTest[1]).isEqualTo("one")
        assertThat(underTest[2]).isEqualTo("three")
    }

    @Test
    fun `dropFirst removes first element from a list`() {
        val underTest = listOf("one", "two", "three")
        val result = underTest.dropFirst()
        assertThat(result).containsExactlyInAnyOrder("two", "three")
    }

    @Test
    fun `dropFirst returns empty list for input with single element`() {
        val underTest = listOf("one")
        val result = underTest.dropFirst()
        assertThat(result).isEmpty()
    }

    @Test
    fun `dropFirst returns empty list for empty list as input`() {
        val underTest = emptyList<String>()
        val result = underTest.dropFirst()
        assertThat(result).isEmpty()
    }
}
