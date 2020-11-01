package com.harishkannarao.kotlin.exercise.sample.concurrency

import assertk.assertThat
import assertk.assertions.isEqualTo
import kotlinx.coroutines.*
import org.testng.annotations.Test
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicLong
import kotlin.system.measureTimeMillis

class SingleThreadedContextTest {

    @Test
    fun `increments long counter using single threaded context`() = runBlocking {
        val counterContext = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
        var fromCounter = 100000L
        var toCounter = 0L
        val transferQuantity = 1L

        withContext(Dispatchers.Default) {
            withContext(counterContext) {
                // confine each massive run to a single-threaded context
                val totalTime = massiveRun(100, 1000) {
                    fromCounter -= transferQuantity
                    toCounter += transferQuantity
                }
                println("Total Time = $totalTime ms")
            }
        }
        println("From Counter = $fromCounter")
        println("To Counter = $toCounter")
        assertThat(fromCounter).isEqualTo(0L)
        assertThat(toCounter).isEqualTo(100000L)
    }

    @Test
    fun `test atomic counter`() = runBlocking {
        val counter = AtomicLong()

        withContext(Dispatchers.Default) {
            val totalTime = massiveRun(100, 1000) {
                counter.incrementAndGet()
            }
            println("Total Time = $totalTime ms")
        }
        println("Counter = $counter")
        assertThat(counter.get()).isEqualTo(100000L)
    }

    private suspend fun massiveRun(numberOfCoroutines: Int = 100, repeatTimes: Int = 1000, action: suspend () -> Unit): Long {
        val time = measureTimeMillis {
            coroutineScope { // scope for coroutines
                repeat(numberOfCoroutines) {
                    launch {
                        repeat(repeatTimes) { action() }
                    }
                }
            }
        }
        println("Completed ${numberOfCoroutines * repeatTimes} actions in $time ms")
        return time
    }
}