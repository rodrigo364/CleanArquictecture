package br.com.network.service

import br.com.network.dto.CharacterAllDto
import retrofit2.Response
import retrofit2.http.GET

interface CharacterService {

    @GET("character")
    suspend fun getAllCharacter() : Response<CharacterAllDto>
}