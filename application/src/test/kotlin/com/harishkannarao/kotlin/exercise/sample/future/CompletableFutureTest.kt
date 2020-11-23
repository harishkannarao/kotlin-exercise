package com.harishkannarao.kotlin.exercise.sample.future

import kotlinx.coroutines.future.await
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.testng.annotations.Test
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors
import kotlin.system.measureTimeMillis


class CompletableFutureTest {
    @Suppress("SameParameterValue")
    private suspend fun calculateAsync(message: String): String {
        val future = CompletableFuture<String>()
        Executors.newCachedThreadPool().submit {
            Thread.sleep(150)
            future.complete("Hello $message")
        }
        return future.await()
    }

    @Test
    fun `testing future to suspend`() = runBlocking {
        val time = measureTimeMillis {
            val result = calculateAsync("World")
            result.shouldBeEqualTo("Hello World")
        }
        println("Time = ${time}ms")
    }
}