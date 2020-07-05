package com.harishkannarao.kotlin.exercise.sample

class TailRecursion {
    fun calculateSum(values: Sequence<Int>): Int {
        return calculateSumInternal(0, values)
    }

    private tailrec fun calculateSumInternal(acc: Int, values: Sequence<Int>): Int {
        return if (values.none()) {
            acc
        } else {
            calculateSumInternal(acc + values.first(), values.drop(1))
        }
    }
}