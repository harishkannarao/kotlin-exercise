package com.harishkannarao.kotlin.exercise.sample

import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

class SampleService(
        private val sampleDao: SampleDao<Boolean>
) {
    fun get(id: String): SampleDto {
        return sampleDao.get(id)
    }

    fun create(dto: SampleDto) {
        if (dto.name.isEmpty()) {
            throw IllegalArgumentException("'name' is empty")
        }
        sampleDao.save(dto, true)
    }
}