package com.harishkannarao.kotlin.exercise.sample

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test

class TailRecursionTest {

    private val underTest = TailRecursion()

    @Test
    fun `calculate returns sum of list items`() {
        val result = underTest.calculateSum(listOf(2, 4, 5))

        assertThat(result, equalTo(11))
    }

    @Test
    fun `calculate returns 0 for empty list`() {
        val result = underTest.calculateSum(emptyList())

        assertThat(result, equalTo(0))
    }
}