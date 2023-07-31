package br.com.network.repository.character

import br.com.network.dto.CharacterAllDto
import retrofit2.Response

interface CharacterRepository {

    suspend fun getAllCharacter() : Response<CharacterAllDto>
}