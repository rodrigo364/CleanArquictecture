package br.com.domain.usecase

import br.com.core.Resource
import br.com.domain.model.CharacterAll
import br.com.domain.model.toDomain
import br.com.network.handleApi
import br.com.network.repository.character.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterUseCase @Inject constructor(
    private val characterNetworkRepository: CharacterRepository
) {
    suspend operator fun invoke (): Flow<Resource<CharacterAll>> = flow {
        val response = handleApi {
            characterNetworkRepository.getAllCharacter()
        }
        when(response) {
            is Resource.Loading -> {
                emit(Resource.Loading())
            }
            is Resource.Success -> {
                emit(Resource.Success(response.data?.toDomain()))
            }
            is Resource.Failure -> {
                emit(Resource.Failure(response.error))
            }
        }

    }
}

