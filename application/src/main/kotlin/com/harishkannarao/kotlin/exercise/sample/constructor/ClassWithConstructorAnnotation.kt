package com.harishkannarao.kotlin.exercise.sample.constructor

data class ClassWithConstructorAnnotation
@SuppressWarnings("test annotation on constructor")
constructor(val field1: String, val field2: String) {
    val field3: String = field1 + field2
    val field4: String

    init {
        field4 = field1 + field2 + field3
    }

    constructor() : this(field1 = "", field2 = "")
}