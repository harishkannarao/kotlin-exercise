package com.harishkannarao.kotlin.exercise.helper

import org.mockito.ArgumentCaptor
import org.mockito.Mockito

object MockitoHelper {
    fun <T> anyObject(): T {
        Mockito.any<T>()
        return uninitialized()
    }
    @Suppress("UNCHECKED_CAST")
    private fun <T> uninitialized(): T =  null as T

    inline fun <reified T: Any> mockGenericClass() = Mockito.mock(T::class.java)

    fun <T> capture(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()
}