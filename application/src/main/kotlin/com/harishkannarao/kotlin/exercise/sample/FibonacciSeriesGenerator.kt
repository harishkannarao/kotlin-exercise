package com.harishkannarao.kotlin.exercise.sample

class FibonacciSeriesGenerator {
    fun generate(input: Long): List<Long> {
        return when {
            input <= 0L -> {
                listOf(0L)
            }
            else -> {
                generateInternal(input, 1L, 1L, 0L, listOf(0L, 1L))
            }
        }
    }

    private tailrec fun generateInternal(input: Long, current: Long, previousFibonacci: Long, secondPreviousFibonacci: Long, acc: List<Long>): List<Long> {
        return if (current > input) {
            acc
        } else {
            if (previousFibonacci + secondPreviousFibonacci == current) {
                generateInternal(input, current + 1, current, previousFibonacci, acc.plus(current))
            } else {
                generateInternal(input, current + 1, previousFibonacci, secondPreviousFibonacci, acc)
            }
        }
    }

}
