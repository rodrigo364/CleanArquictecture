package br.com.network.di

import br.com.network.repository.character.CharacterRepository
import br.com.network.repository.character.CharacterRepositoryImpl
import br.com.network.service.CharacterService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {


    @Provides
    @Singleton
    fun provideCharacterRepositoryImpl(
        service: CharacterService
    ) : CharacterRepository {
        return CharacterRepositoryImpl(service)
    }
}