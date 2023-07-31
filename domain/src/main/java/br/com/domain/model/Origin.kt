package br.com.domain.model

import br.com.network.dto.OriginDto

data  class Origin(
    val name: String,
    val url: String
)

fun OriginDto.todomain() : Origin {
    return Origin(
        name = name,
        url = url
    )
}