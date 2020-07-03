package com.harishkannarao.kotlin.exercise.sample

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test

class CharacterCalculatorTest {
    private val underTest = CharacterCalculator()

    @Test
    internal fun `calculateMaxOccurrence returns max sequence of every character in the string`() {
        val input = "aabbbaccccd"
        val result = underTest.calculateMaxOccurrence(input)

        assertThat(result['a'], equalTo(2))
        assertThat(result['b'], equalTo(3))
        assertThat(result['c'], equalTo(4))
        assertThat(result['d'], equalTo(1))
    }

    @Test
    internal fun `calculateMaxOccurrence returns empty map for blank string`() {
        assertThat(underTest.calculateMaxOccurrence("  ").isEmpty(), equalTo(true))
    }

    @Test
    internal fun `calculateMaxOccurrence ignores white spaces in the string`() {
        val input = "aa  ba cc"
        val result = underTest.calculateMaxOccurrence(input)

        assertThat(result.containsKey(' '), equalTo(false))
        assertThat(result['a'], equalTo(2))
        assertThat(result['b'], equalTo(1))
        assertThat(result['c'], equalTo(2))
    }
}