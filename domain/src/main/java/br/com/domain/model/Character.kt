package br.com.domain.model

import br.com.network.dto.CharacterDto


data class Character (
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)

fun CharacterDto.toDomain() : Character {
    return Character(
        created = created,
        episode = episode,
        gender = gender,
        id = id,
        image = image,
        location = location.toDomain(),
        name = name,
        origin = origin.todomain(),
        species = species,
        status = status,
        type = type,
        url = url
    )

}