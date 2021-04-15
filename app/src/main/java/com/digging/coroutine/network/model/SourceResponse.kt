package com.digging.coroutine.network.model

import com.digging.coroutine.domain.model.SourceModel
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SourceResponse(
    val id: String? = null,
    val name: String? = null
)

internal fun SourceResponse.toDomain(): SourceModel {
    return SourceModel(
        id = id,
        name = name
    )
}