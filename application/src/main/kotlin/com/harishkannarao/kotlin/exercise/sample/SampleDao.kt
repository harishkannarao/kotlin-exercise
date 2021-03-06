package com.harishkannarao.kotlin.exercise.sample

class SampleDao<T> {
    fun get(id: String): SampleDto {
        return SampleDto(
                id = id,
                name = "name-$id"
        )
    }

    @Suppress("UNUSED_PARAMETER")
    fun save(dto: SampleDto, ret: T): T {
        return ret
    }
}