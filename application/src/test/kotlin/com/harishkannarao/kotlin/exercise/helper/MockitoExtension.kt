package com.harishkannarao.kotlin.exercise.helper

import org.mockito.ArgumentCaptor

fun <T> ArgumentCaptor<T>.captureNonNullable(): T = this.capture()