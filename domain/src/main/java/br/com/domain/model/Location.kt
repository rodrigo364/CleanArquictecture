package br.com.domain.model

import br.com.network.dto.LocationDto

data class Location (
    val name: String,
    val url: String
)

fun LocationDto.toDomain() : Location {
    return Location(
        name = name,
        url = url
    )
}