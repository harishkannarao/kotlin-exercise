package com.harishkannarao.kotlin.exercise.sample

class SampleService(
        private val sampleDao: SampleDao<Boolean>,
        private val sampleHttpClient: SampleHttpClient
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

    fun createMany(inputs: List<SampleDto>) {
        TODO("Not yet implemented")
    }
}