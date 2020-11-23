package com.harishkannarao.kotlin.exercise.sample.concurrency

import kotlinx.coroutines.*
import org.amshove.kluent.shouldBeEqualTo
import org.testng.annotations.Test
import java.util.concurrent.Executors
import kotlin.random.Random
import kotlin.system.measureTimeMillis

class MoneyExchangeTest {
    @Test
    fun `test atomic transfer in concurrent sessions`() = runBlocking {
        val firstAccId = "Account1"
        val secondAccId = "Account2"
        val initialState = mapOf(
                Pair(firstAccId, 100000L),
                Pair(secondAccId, 0L)
        )
        val singleThreadedContext = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
        val underTest = MoneyExchange(initialState = initialState)
        val time = measureTimeMillis {
            withContext(Dispatchers.Default) {
                repeat(100) {
                    withContext(singleThreadedContext) {
                        launch {
                            repeat(1000) {
                                val transferAmount = Random.nextLong(1, 5)
                                underTest.transfer(firstAccId, secondAccId, transferAmount)
                            }
                        }
                    }
                }
            }
        }
        println("Time = $time")
        val account1Balance = underTest.getBalance(firstAccId)
        val account2Balance = underTest.getBalance(secondAccId)
        val totalTransactions = underTest.getTotalTransaction()
        println("Balance Account1 = $account1Balance")
        println("Balance Account2 = $account2Balance")
        println("Total Transaction = $totalTransactions")
        account1Balance + account2Balance.shouldBeEqualTo(100000L)
        account1Balance.shouldBeEqualTo(0L)
        account2Balance.shouldBeEqualTo(100000L)
        totalTransactions.shouldBeEqualTo(100000L)
    }
}