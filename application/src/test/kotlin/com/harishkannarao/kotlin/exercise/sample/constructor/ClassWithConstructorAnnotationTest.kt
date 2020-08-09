package com.harishkannarao.kotlin.exercise.sample.constructor

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test

class ClassWithConstructorAnnotationTest {

    @Test
    fun `test primary constructor`() {
        val underTest = ClassWithConstructorAnnotation(field1 = "123", field2 = "456")
        assertThat(underTest.field3, equalTo("123456"))
        assertThat(underTest.field4, equalTo("123456123456"))
    }

    @Test
    fun `test secondary constructor with no arguments`() {
        val underTest = ClassWithConstructorAnnotation()
        assertThat(underTest.field3, equalTo(""))
        assertThat(underTest.field4, equalTo(""))
    }
}