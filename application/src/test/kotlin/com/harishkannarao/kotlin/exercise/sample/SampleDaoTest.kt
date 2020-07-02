package com.harishkannarao.kotlin.exercise.sample

import com.harishkannarao.kotlin.exercise.sample.SampleDao
import com.harishkannarao.kotlin.exercise.sample.SampleDto
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test

class SampleDaoTest {

    private val underTest = SampleDao<Boolean>()

    @Test
    fun `get returns a valid dto`() {
        val inputId = "test-id"
        val result = underTest.get(inputId)

        assertThat(result.id, equalTo(inputId))
        assertThat(result.name, equalTo("name-$inputId"))
    }

    @Test
    fun `save returns the input value`() {
        val inputDto = SampleDto(
                "test-id",
                "test-name"
        )
        val result = underTest.save(inputDto, true)

        assertThat(result, equalTo(true))
    }
}