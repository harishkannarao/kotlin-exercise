package com.harishkannarao.kotlin.exercise

import com.harishkannarao.kotlin.exercise.helper.MockitoHelper
import com.harishkannarao.kotlin.exercise.sample.SampleDao
import com.harishkannarao.kotlin.exercise.sample.SampleDto
import com.harishkannarao.kotlin.exercise.sample.SampleService
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.*

class SampleServiceTest {

    private lateinit var underTest: SampleService
    private lateinit var mockSampleDao: SampleDao

    @BeforeEach
    internal fun setUp() {
        mockSampleDao = mock(SampleDao::class.java)
        underTest = SampleService(mockSampleDao)
    }

    @Test
    fun `get returns value from data store`() {
        val id = "test-id"
        val expectedDto = SampleDto(
                id = id,
                name = "test-name"
        )
        `when`(mockSampleDao.get(id)).thenReturn(expectedDto)
        val result: SampleDto = underTest.get(id)

        assertThat(result, equalTo(expectedDto))
    }

    @Test
    fun `create saves valid value into data store`() {
        val inputDto = SampleDto(
                "test-id",
                "name-test"
        )

        val uuidCaptor: ArgumentCaptor<SampleDto> = ArgumentCaptor.forClass(SampleDto::class.java)
        doNothing().`when`(mockSampleDao).save(MockitoHelper.capture(uuidCaptor))

        underTest.create(inputDto)

        assertThat(uuidCaptor.allValues, contains(inputDto))
    }

    @Test
    fun `create throws error for empty name and doesn't save in data store`() {
        val inputWithEmptyName = SampleDto(
                "test-id",
                ""
        )

        val result = assertThrows(IllegalArgumentException::class.java) { underTest.create(inputWithEmptyName) }

        assertThat(result.message, equalTo("'name' is empty"))

        verify(mockSampleDao, times(0)).save(MockitoHelper.anyObject())
    }
}