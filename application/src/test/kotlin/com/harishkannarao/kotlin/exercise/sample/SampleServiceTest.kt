package com.harishkannarao.kotlin.exercise.sample

import com.nhaarman.mockitokotlin2.*
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.contains
import org.hamcrest.Matchers.equalTo
import org.testng.Assert.fail
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

class SampleServiceTest {

    private lateinit var underTest: SampleService
    private lateinit var mockSampleDao: SampleDao<Boolean>
    private lateinit var mockSampleHttpClient: SampleHttpClient

    @BeforeMethod(alwaysRun = true)
    fun setUp() {
        mockSampleDao = mock()
        mockSampleHttpClient = mock()
        underTest = SampleService(mockSampleDao, mockSampleHttpClient)
    }

    @Test
    fun `get returns value from data store`() {
        val id = "test-id"
        val expectedDto = SampleDto(id = id, name = "test-name")

        whenever(mockSampleDao.get(id)).thenReturn(expectedDto)

        val result: SampleDto = underTest.get(id)

        assertThat(result, equalTo(expectedDto))
    }

    @Test
    fun `create saves valid value into data store`() {
        val inputDto = SampleDto("test-id", "name-test")

        whenever(mockSampleDao.save(any(), any())).thenReturn(true)

        underTest.create(inputDto)

        val dtoCaptor = argumentCaptor<SampleDto>()
        val booleanCaptor = argumentCaptor<Boolean>()
        verify(mockSampleDao).save(dtoCaptor.capture(), booleanCaptor.capture())

        assertThat(dtoCaptor.allValues, contains(inputDto))
        assertThat(booleanCaptor.allValues, contains(true))
    }

    @Test
    fun `create throws error for empty name and doesn't save in data store`() {
        val inputWithEmptyName = SampleDto("test-id", "")
        try {
            underTest.create(inputWithEmptyName)
            fail("should throw exception")
        } catch (result: IllegalArgumentException) {
            assertThat(result.message, equalTo("'name' is empty"))
        }
        verify(mockSampleDao, times(0)).save(any(), any())
    }

    @Test
    fun `createMany saves multiple through http`() {
        val dto1 = SampleDto("id1", "name1")
        val dto2 = SampleDto("id2", "name2")
        val input = listOf(dto1, dto2)

        underTest.createMany(input)

        val listCaptor = argumentCaptor<List<SampleDto>>()
        verify(mockSampleHttpClient).saveAll(listCaptor.capture())
        assertThat(listCaptor.allValues.size, equalTo(1))
        assertThat(listCaptor.allValues.first(), contains(*input.toTypedArray()))
    }

    @Test
    fun `createMany does not save on empty list`() {
        underTest.createMany(emptyList())

        verify(mockSampleHttpClient, times(0)).saveAll(any())
    }
}