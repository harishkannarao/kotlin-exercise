package com.harishkannarao.kotlin.exercise.sample.concurrency

import kotlinx.coroutines.*
import org.amshove.kluent.shouldBeEqualTo
import org.testng.annotations.Test
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicLong
import kotlin.random.Random
import kotlin.system.measureTimeMillis

class SingleThreadedContextTest {

    @Test
    fun `increments long counter using single threaded context`() = runBlocking {
        val singleThreadedContext = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
        var fromAccount = 100000L
        var toAccount = 0L

        withContext(Dispatchers.Default) {
            // confine each massive run to a single-threaded context
            withContext(singleThreadedContext) {
                val totalTime = massiveRun(100, 1000) {
                    val transferAmount = Random.nextInt(1, 5)
                    if (fromAccount >= transferAmount) {
                        fromAccount -= transferAmount
                        toAccount += transferAmount
                    }
                }
                println("Total Time = $totalTime ms")
            }
        }
        println("From Counter = $fromAccount")
        println("To Counter = $toAccount")
        (toAccount + fromAccount).shouldBeEqualTo(100000L)
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
        counter.get().shouldBeEqualTo(100000L)
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