package com.budi.setiawan.core.data.source.remote.mapper

interface EntityMapper<in R, out E> {
    fun mapRemoteToEntity(remote: R): E

    fun mapRemoteToEntities(remotes: List<R>): List<E>
}
