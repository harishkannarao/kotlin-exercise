package com.harishkannarao.kotlin.exercise.sample.constructor

import org.amshove.kluent.shouldBeEqualTo
import org.testng.annotations.Test

class SubClassTest {

    @Test
    fun `test primary constructor`() {
        val underTest = SubClass(field1 = "123", field2 = "456")
        underTest.field3.shouldBeEqualTo("123456")
        underTest.field4.shouldBeEqualTo("123456123456")
        underTest.field5.shouldBeEqualTo("456123")
    }

    @Test
    fun `test secondary constructor with no arguments`() {
        val underTest = SubClass()
        underTest.field3.shouldBeEqualTo("")
        underTest.field4.shouldBeEqualTo("")
        underTest.field5.shouldBeEqualTo("")
    }
}