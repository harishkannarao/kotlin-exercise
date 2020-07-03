package com.harishkannarao.kotlin.exercise.sample

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test

class MaxLetterSequenceTest {
    val underTest = MaxLetterSequence()

    @Test
    internal fun `calculate returns max sequence of every character in the string`() {
        val input = "aabbbaccccd"
        val result = underTest.calculate(input)

        assertThat(result['a'], equalTo(2))
        assertThat(result['b'], equalTo(3))
        assertThat(result['c'], equalTo(4))
        assertThat(result['d'], equalTo(1))
    }

    @Test
    internal fun `calculate returns empty map for blank string`() {
        assertThat(underTest.calculate("  ").isEmpty(), equalTo(true))
    }

    @Test
    internal fun `calculate ignores white spaces in the string`() {
        val input = "aa  ba cc"
        val result = underTest.calculate(input)

        assertThat(result.containsKey(' '), equalTo(false))
        assertThat(result['a'], equalTo(2))
        assertThat(result['b'], equalTo(1))
        assertThat(result['c'], equalTo(2))
    }
}