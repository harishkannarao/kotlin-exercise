package com.harishkannarao.kotlin.exercise.sample

import io.mockk.*
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

class SampleServiceTest {

    private lateinit var underTest: SampleService
    private lateinit var mockSampleDao: SampleDao<Boolean>
    private lateinit var mockSampleHttpClient: SampleHttpClient

    @BeforeMethod(alwaysRun = true)
    fun setUp() {
        mockSampleDao = mockk()
        mockSampleHttpClient = mockk()
        underTest = SampleService(mockSampleDao, mockSampleHttpClient)
    }

    @Test
    fun `get returns value from data store`() {
        val id = "test-id"
        val expectedDto = SampleDto(id = id, name = "test-name")

        every { mockSampleDao.get(id) } returns expectedDto

        val result: SampleDto = underTest.get(id)

        result.shouldBeEqualTo(expectedDto)
    }

    @Test
    fun `create saves valid value into data store`() {
        val inputDto = SampleDto("test-id", "name-test")
        val dtoCaptor = mutableListOf<SampleDto>()
        val booleanCaptor = mutableListOf<Boolean>()

        every { mockSampleDao.hint(Boolean::class).save(capture(dtoCaptor), capture(booleanCaptor)) } returns true

        underTest.create(inputDto)

        verify { mockSampleDao.save(any(), any()) }

        dtoCaptor.toList().shouldBeEqualTo(listOf(inputDto))
        booleanCaptor.toList().shouldBeEqualTo(listOf(true))
    }

    @Test
    fun `create throws error for empty name and doesn't save in data store`() {
        val inputWithEmptyName = SampleDto("test-id", "")
        val result = invoking {
            underTest.create(inputWithEmptyName)
        }.shouldThrow(IllegalArgumentException::class)

        result.exception.message.shouldBeEqualTo("'name' is empty")

        verify(exactly = 0) { mockSampleDao.save(any(), any()) }
    }

    @Test
    fun `createMany saves multiple through http`() {
        val dto1 = SampleDto("id1", "name1")
        val dto2 = SampleDto("id2", "name2")
        val input = listOf(dto1, dto2)
        val listCaptor = mutableListOf<List<SampleDto>>()

        every { mockSampleHttpClient.saveAll(capture(listCaptor)) } just Runs

        underTest.createMany(input)

        verify { mockSampleHttpClient.saveAll(any()) }

        listCaptor.toList().size.shouldBeEqualTo(1)
        listCaptor.toList().first().toList().shouldBeEqualTo(input)
    }

    @Test
    fun `createMany does not save on empty list`() {
        underTest.createMany(emptyList())

        verify(exactly = 0) { mockSampleHttpClient.saveAll(any()) }
    }
}