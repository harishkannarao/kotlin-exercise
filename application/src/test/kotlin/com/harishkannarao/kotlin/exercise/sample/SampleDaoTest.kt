package com.harishkannarao.kotlin.exercise.sample

import org.amshove.kluent.shouldBeEqualTo
import org.testng.annotations.Test

class SampleDaoTest {

    private val underTest = SampleDao<Boolean>()

    @Test
    fun `get returns a valid dto`() {
        val inputId = "test-id"
        val result = underTest.get(inputId)

        result.id.shouldBeEqualTo(inputId)
        result.name.shouldBeEqualTo("name-$inputId")
    }

    @Test
    fun `save returns the input value`() {
        val inputDto = SampleDto(
                "test-id",
                "test-name"
        )
        val result = underTest.save(inputDto, true)

        result.shouldBeEqualTo(true)
    }
}