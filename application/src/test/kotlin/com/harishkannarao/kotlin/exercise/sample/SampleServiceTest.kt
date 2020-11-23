package com.harishkannarao.kotlin.exercise.sample

import com.nhaarman.mockitokotlin2.argumentCaptor
import org.amshove.kluent.*
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

        When.calling(mockSampleDao.get(id)).itReturns(expectedDto)

        val result: SampleDto = underTest.get(id)

        result.shouldBeEqualTo(expectedDto)
    }

    @Test
    fun `create saves valid value into data store`() {
        val inputDto = SampleDto("test-id", "name-test")

        When.calling(mockSampleDao.save(any(), any())).itReturns(true)

        underTest.create(inputDto)

        val dtoCaptor = argumentCaptor<SampleDto>()
        val booleanCaptor = argumentCaptor<Boolean>()
        Verify.on(mockSampleDao).save(dtoCaptor.capture(), booleanCaptor.capture())

        dtoCaptor.allValues.shouldContainAll(listOf(inputDto))
        booleanCaptor.allValues.shouldContainAll(listOf(true))
    }

    @Test
    fun `create throws error for empty name and doesn't save in data store`() {
        val inputWithEmptyName = SampleDto("test-id", "")
        val result = invoking {
            underTest.create(inputWithEmptyName)
        }.shouldThrow(IllegalArgumentException::class)
        result.exception.message.shouldBeEqualTo("'name' is empty")

        Verify.times(0).on(mockSampleDao).save(any(), any())
    }

    @Test
    fun `createMany saves multiple through http`() {
        val dto1 = SampleDto("id1", "name1")
        val dto2 = SampleDto("id2", "name2")
        val input = listOf(dto1, dto2)

        underTest.createMany(input)

        val listCaptor = argumentCaptor<List<SampleDto>>()
        Verify.on(mockSampleHttpClient).saveAll(listCaptor.capture())

        listCaptor.allValues.size.shouldBeEqualTo(1)
        listCaptor.allValues.first().toList().shouldContainSame(input)
    }

    @Test
    fun `createMany does not save on empty list`() {
        underTest.createMany(emptyList())

        Verify.times(0).on(mockSampleHttpClient).saveAll(any())
    }
}