package com.budi.setiawan.core.data.source.local.mapper

interface DataMapper<E, M> {

    fun mapFromEntity(entity: E): M

    fun mapFromEntities(entities: List<E>): List<M>

    fun mapToEntity(model: M): E
}