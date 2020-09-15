package com.harishkannarao.kotlin.exercise.sample

import assertk.assertThat
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import org.testng.annotations.Test

class CharacterCalculatorTest {
    private val underTest = CharacterCalculator()

    @Test
    fun `calculateMaxOccurrence returns max sequence of every character in the string`() {
        val input = "aabbbaccccd"
        val result = underTest.calculateMaxOccurrence(input)

        assertThat(result['a']).isEqualTo(2)
        assertThat(result['b']).isEqualTo(3)
        assertThat(result['c']).isEqualTo(4)
        assertThat(result['d']).isEqualTo(1)
    }

    @Test
    fun `calculateMaxOccurrence returns empty map for blank string`() {
        assertThat(underTest.calculateMaxOccurrence("  ")).isEmpty()
    }

    @Test
    fun `calculateMaxOccurrence ignores white spaces in the string`() {
        val input = "aa  ba cc"
        val result = underTest.calculateMaxOccurrence(input)

        assertThat(result.containsKey(' ')).isEqualTo(false)
        assertThat(result['a']).isEqualTo(2)
        assertThat(result['b']).isEqualTo(1)
        assertThat(result['c']).isEqualTo(2)
    }

    @Test
    fun `calculatorTotalOccurrence returns total occurrence of every character`() {
        val input = "aabbcdabce"

        val result: Map<Char, Int> = underTest.calculateTotalOccurrence(input)

        assertThat(result['a']).isEqualTo(3)
        assertThat(result['b']).isEqualTo(3)
        assertThat(result['c']).isEqualTo(2)
        assertThat(result['d']).isEqualTo(1)
        assertThat(result['e']).isEqualTo(1)
    }

    @Test
    fun `calculateTotalOccurrence returns empty map for blank string`() {
        assertThat(underTest.calculateTotalOccurrence("  ")).isEmpty()
    }

    @Test
    fun `calculateTotalOccurrence ignores whitespace character`() {
        val input = "aab bc"

        val result: Map<Char, Int> = underTest.calculateTotalOccurrence(input)

        assertThat(result.containsKey(' ')).isEqualTo(false)
        assertThat(result['a']).isEqualTo(2)
        assertThat(result['b']).isEqualTo(2)
        assertThat(result['c']).isEqualTo(1)
    }
}