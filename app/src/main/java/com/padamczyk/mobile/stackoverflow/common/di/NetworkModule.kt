package com.padamczyk.mobile.stackoverflow.common.di

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.padamczyk.mobile.stackoverflow.common.repository.StackoverflowApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesConverter(): Converter.Factory {
        return JacksonConverterFactory.create(
                ObjectMapper().
                        registerModule(KotlinModule()).
                        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        )
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(converterFactory: Converter.Factory, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().
                addConverterFactory(converterFactory).
                baseUrl("https://api.stackexchange.com/").
                client(okHttpClient).
                build()
    }

    @Provides
    @Singleton
    fun providesStackoverflowApi(retrofit: Retrofit) = retrofit.create(StackoverflowApi::class.java)
}
