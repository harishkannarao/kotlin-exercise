package com.harishkannarao.kotlin.exercise.sample

import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test

class FibonacciSeriesGeneratorTest {

    private val underTest = FibonacciSeriesGenerator()

    @Test
    fun `returns fibonacci series less than given input value when input is not a fibonacci number`() {
        val result = underTest.generate(10L)

        MatcherAssert.assertThat(result, Matchers.contains(0L, 1L, 1L, 2L, 3L, 5L, 8L))
    }

    @Test
    fun `returns fibonacci series up to the given input value when input is a fibonacci number`() {
        val result = underTest.generate(8L)

        MatcherAssert.assertThat(result, Matchers.contains(0L, 1L, 1L, 2L, 3L, 5L, 8L))
    }

    @Test
    fun `returns 0 for input value 0`() {
        val result = underTest.generate(0L)

        MatcherAssert.assertThat(result, Matchers.contains(0L))
    }

    @Test
    fun `returns 0,1,1 for input value 1`() {
        val result = underTest.generate(1L)

        MatcherAssert.assertThat(result, Matchers.contains(0L, 1L, 1L))
    }

    @Test
    fun `returns 0 for negative input value`() {
        val result = underTest.generate(-1L)

        MatcherAssert.assertThat(result, Matchers.contains(0L))
    }
}