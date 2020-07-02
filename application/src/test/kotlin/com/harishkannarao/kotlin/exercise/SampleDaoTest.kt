package com.harishkannarao.kotlin.exercise

import com.harishkannarao.kotlin.exercise.sample.SampleDao
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test

class SampleDaoTest {

    private val underTest = SampleDao()

    @Test
    internal fun `get returns a valid dto`() {
        val inputId = "test-id"
        val result = underTest.get(inputId)

        assertThat(result.id, equalTo(inputId))
        assertThat(result.name, equalTo("name-$inputId"))
    }
}