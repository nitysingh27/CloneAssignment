package com.example.cloneassignment.di

import com.example.cloneassignment.repository.MainRepository
import com.example.cloneassignment.retrofit.DocumentRetrofitApiCalls
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(documentRetrofitApiCalls: DocumentRetrofitApiCalls):MainRepository{
        return MainRepository(documentRetrofitApiCalls)
    }

}