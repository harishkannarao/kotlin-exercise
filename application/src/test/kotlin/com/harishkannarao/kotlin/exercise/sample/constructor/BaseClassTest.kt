package com.harishkannarao.kotlin.exercise.sample.constructor

import org.amshove.kluent.shouldBeEqualTo
import org.testng.annotations.Test

class BaseClassTest {

    @Test
    fun `test primary constructor`() {
        val underTest = BaseClass(field1 = "123", field2 = "456")
        underTest.field3.shouldBeEqualTo("123456")
        underTest.field4.shouldBeEqualTo("123456123456")
    }

    @Test
    fun `test secondary constructor with no arguments`() {
        val underTest = BaseClass()
        underTest.field3.shouldBeEqualTo("")
        underTest.field4.shouldBeEqualTo("")
    }
}