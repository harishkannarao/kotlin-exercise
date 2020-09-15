package com.harishkannarao.kotlin.exercise.sample

import assertk.assertThat
import assertk.assertions.containsExactlyInAnyOrder
import assertk.assertions.isEqualTo
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.testng.annotations.Test
import java.math.BigDecimal

class ConcurrentAtomicOperationsTest {

    private val underTest: ConcurrentAtomicOperations = ConcurrentAtomicOperations()

    @Test
    fun `increments long counter in atomic way`() {
        val result1 = GlobalScope.async {
            underTest.incrementLong()
        }
        val result2 = GlobalScope.async {
            underTest.incrementLong()
        }
        runBlocking {
            val result = listOf(result1.await(), result2.await())
            assertThat(result).containsExactlyInAnyOrder(1L, 2L)
        }
    }

    @Test
    fun `increments big decimal counter in atomic way`() {
        val result1 = GlobalScope.async {
            underTest.incrementBigDecimal(BigDecimal.valueOf(2.00))
        }
        val result2 = GlobalScope.async {
            underTest.incrementBigDecimal(BigDecimal.valueOf(2.00))
        }
        runBlocking {
            val result = listOf(result1.await(), result2.await())
            assertThat(result).containsExactlyInAnyOrder(BigDecimal.valueOf(2.00), BigDecimal.valueOf(4.00))
        }
    }

    @Test
    fun `adds items to list in concurrent safe way`() {
        val result1 = GlobalScope.async {
            underTest.addString("value1")
        }
        val result2 = GlobalScope.async {
            underTest.addString("value2")
        }
        runBlocking {
            assertThat(result1.await()).isEqualTo(true)
            assertThat(result2.await()).isEqualTo(true)
            assertThat(underTest.getAllString()).containsExactlyInAnyOrder("value1", "value2")
        }
    }
}