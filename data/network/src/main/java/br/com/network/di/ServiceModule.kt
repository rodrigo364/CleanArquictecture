package br.com.network.di

import br.com.network.service.CharacterService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun providesCharacterService(
        retrofit: Retrofit
    ): CharacterService = retrofit.create(CharacterService::class.java)
}