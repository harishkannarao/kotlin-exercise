package com.harishkannarao.kotlin.exercise.sample.constructor

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.testng.annotations.Test

class SubClassTest {

    @Test
    fun `test primary constructor`() {
        val underTest = SubClass(field1 = "123", field2 = "456")
        assertThat(underTest.field3, equalTo("123456"))
        assertThat(underTest.field4, equalTo("123456123456"))
        assertThat(underTest.field5, equalTo("456123"))
    }

    @Test
    fun `test secondary constructor with no arguments`() {
        val underTest = SubClass()
        assertThat(underTest.field3, equalTo(""))
        assertThat(underTest.field4, equalTo(""))
        assertThat(underTest.field5, equalTo(""))
    }
}