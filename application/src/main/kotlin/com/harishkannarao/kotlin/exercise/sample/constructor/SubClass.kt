package com.harishkannarao.kotlin.exercise.sample.constructor

class SubClass(field1: String, field2: String): BaseClass(field1, field2) {
    val field5: String = field2 + field1

    constructor(): this("", "")
}