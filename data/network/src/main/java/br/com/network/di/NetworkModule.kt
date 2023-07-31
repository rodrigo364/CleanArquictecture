package br.com.network.di

import android.util.Config.DEBUG
import br.com.network.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesHttpClient() : OkHttpClient {

           val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
               level = if (DEBUG) HttpLoggingInterceptor.Level.BODY
               else HttpLoggingInterceptor.Level.NONE
           }

       return OkHttpClient
           .Builder()
           .addInterceptor(httpLoggingInterceptor)
           .readTimeout(15,TimeUnit.SECONDS)
           .connectTimeout(15,TimeUnit.SECONDS)
           .build()
    }


    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .create()
    }

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient,
    gson: Gson) : Retrofit {

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }




}