package com.harishkannarao.kotlin.exercise.sample.constructor

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.testng.annotations.Test

class SubClassTest {

    @Test
    fun `test primary constructor`() {
        val underTest = SubClass(field1 = "123", field2 = "456")
        assertThat(underTest.field3).isEqualTo("123456")
        assertThat(underTest.field4).isEqualTo("123456123456")
        assertThat(underTest.field5).isEqualTo("456123")
    }

    @Test
    fun `test secondary constructor with no arguments`() {
        val underTest = SubClass()
        assertThat(underTest.field3).isEqualTo("")
        assertThat(underTest.field4).isEqualTo("")
        assertThat(underTest.field5).isEqualTo("")
    }
}