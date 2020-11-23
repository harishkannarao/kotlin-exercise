package com.harishkannarao.kotlin.exercise.sample

import org.amshove.kluent.shouldBeEmpty
import org.amshove.kluent.shouldBeEqualTo
import org.testng.annotations.Test

class CharacterCalculatorTest {
    private val underTest = CharacterCalculator()

    @Test
    fun `calculateMaxOccurrence returns max sequence of every character in the string`() {
        val input = "aabbbaccccd"
        val result = underTest.calculateMaxOccurrence(input)

        result['a'].shouldBeEqualTo(2)
        result['b'].shouldBeEqualTo(3)
        result['c'].shouldBeEqualTo(4)
        result['d'].shouldBeEqualTo(1)
    }

    @Test
    fun `calculateMaxOccurrence returns empty map for blank string`() {
        underTest.calculateMaxOccurrence("  ").shouldBeEmpty()
    }

    @Test
    fun `calculateMaxOccurrence ignores white spaces in the string`() {
        val input = "aa  ba cc"
        val result = underTest.calculateMaxOccurrence(input)

        result.containsKey(' ').shouldBeEqualTo(false)
        result['a'].shouldBeEqualTo(2)
        result['b'].shouldBeEqualTo(1)
        result['c'].shouldBeEqualTo(2)
    }

    @Test
    fun `calculatorTotalOccurrence returns total occurrence of every character`() {
        val input = "aabbcdabce"

        val result: Map<Char, Int> = underTest.calculateTotalOccurrence(input)

        result['a'].shouldBeEqualTo(3)
        result['b'].shouldBeEqualTo(3)
        result['c'].shouldBeEqualTo(2)
        result['d'].shouldBeEqualTo(1)
        result['e'].shouldBeEqualTo(1)
    }

    @Test
    fun `calculateTotalOccurrence returns empty map for blank string`() {
        underTest.calculateTotalOccurrence("  ").shouldBeEmpty()
    }

    @Test
    fun `calculateTotalOccurrence ignores whitespace character`() {
        val input = "aab bc"

        val result: Map<Char, Int> = underTest.calculateTotalOccurrence(input)

        result.containsKey(' ').shouldBeEqualTo(false)
        result['a'].shouldBeEqualTo(2)
        result['b'].shouldBeEqualTo(2)
        result['c'].shouldBeEqualTo(1)
    }
}