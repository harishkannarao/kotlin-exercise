package com.harishkannarao.kotlin.exercise.sample

import java.math.BigDecimal
import java.util.concurrent.ConcurrentLinkedDeque
import java.util.concurrent.atomic.AtomicLong
import java.util.concurrent.atomic.AtomicReference

class ConcurrentAtomicOperations {
    private val longCounter: AtomicLong = AtomicLong(0L)
    private val bigDecimalCounter: AtomicReference<BigDecimal> = AtomicReference(BigDecimal.valueOf(0.00))
    private val stringList: ConcurrentLinkedDeque<String> = ConcurrentLinkedDeque()

    fun incrementLong(): Long {
        return longCounter.incrementAndGet()
    }

    fun incrementBigDecimal(value: BigDecimal): BigDecimal {
        return bigDecimalCounter.updateAndGet { bigDecimal -> bigDecimal + value }
    }

    fun addString(value: String): Boolean {
        return stringList.add(value)
    }

    fun getAllString(): List<String> {
        return stringList.toList()
    }
}