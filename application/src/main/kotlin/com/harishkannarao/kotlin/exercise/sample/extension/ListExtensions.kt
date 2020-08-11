package com.harishkannarao.kotlin.exercise.sample.extension

fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
    val tmp = this[index1]
    this[index1] = this[index2]
    this[index2] = tmp
}

object ListExtensions {
    fun <T> List<T>.dropFirst(): List<T> {
        return this.drop(1)
    }
}