package com.harishkannarao.kotlin.exercise.sample.concurrency

class MoneyExchange(initialState: Map<String, Long>) {
    private val accounts = initialState.toMutableMap()
    @Volatile
    private var transactionCounter = 0L

    fun transfer(from: String, to: String, value: Long) {
        if (value <= getBalance(from)) {
            accounts[from] = getBalance(from) - value
            accounts[to] = getBalance(to) + value
        }
        transactionCounter += 1
    }

    fun getBalance(account: String): Long {
        return accounts.getValue(account)
    }

    fun getTotalTransaction(): Long {
        return transactionCounter
    }
}