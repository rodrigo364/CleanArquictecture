package br.com.domain.model

import br.com.network.dto.InfoDto

data class Info(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: Int?
)

fun InfoDto.toDomain() : Info {
    return Info(
        count = count,
        next = next,
        pages = pages,
        prev = prev
    )
}