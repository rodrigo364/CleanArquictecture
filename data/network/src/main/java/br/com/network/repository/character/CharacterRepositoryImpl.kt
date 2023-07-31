package br.com.network.repository.character

import br.com.core.Resource
import br.com.network.dto.CharacterAllDto
import br.com.network.handleApi
import br.com.network.service.CharacterService
import retrofit2.Response
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor (
    private val serviceCharacter: CharacterService
) : CharacterRepository {
    override suspend fun getAllCharacter(): Response<CharacterAllDto> {
            return serviceCharacter.getAllCharacter()
    }

}