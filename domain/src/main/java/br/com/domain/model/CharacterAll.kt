package br.com.domain.model

import br.com.network.dto.CharacterAllDto


data class CharacterAll(
    val info: Info,
    val results: List<Character>
)

fun CharacterAllDto.toDomain() : CharacterAll {
    return CharacterAll(
        info = info.toDomain(),
        results = results.map { it.toDomain() }
    )
}