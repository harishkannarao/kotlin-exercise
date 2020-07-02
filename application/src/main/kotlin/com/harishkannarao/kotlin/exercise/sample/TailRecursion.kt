package com.harishkannarao.kotlin.exercise.sample

class TailRecursion {
    fun calculateSum(values: List<Int>): Int {
        return calculateSumInternal(0, values)
    }

    private tailrec fun calculateSumInternal(acc: Int, values: List<Int>): Int {
        return if (values.isNotEmpty()) {
            calculateSumInternal(acc + values.first(), values.takeLast(values.size - 1))
        } else {
            acc
        }
    }
}