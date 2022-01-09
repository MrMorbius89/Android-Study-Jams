package com.example.firstfm.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.converter.gson.GsonConverterFactory

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ConverterFactoryModule {
    @Provides
    @Singleton
    fun provideConverterFactory():GsonConverterFactory {
        return GsonConverterFactory.create()
    }
}