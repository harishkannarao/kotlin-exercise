package com.harishkannarao.kotlin.exercise.sample.constructor

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.testng.annotations.Test

class BaseClassTest {

    @Test
    fun `test primary constructor`() {
        val underTest = BaseClass(field1 = "123", field2 = "456")
        assertThat(underTest.field3, equalTo("123456"))
        assertThat(underTest.field4, equalTo("123456123456"))
    }

    @Test
    fun `test secondary constructor with no arguments`() {
        val underTest = BaseClass()
        assertThat(underTest.field3, equalTo(""))
        assertThat(underTest.field4, equalTo(""))
    }
}