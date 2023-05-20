package com.example.cloneassignment.di

import com.example.cloneassignment.retrofit.BasicAuthInterceptor
import com.example.cloneassignment.retrofit.DocumentRetrofitApiCalls
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
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder().create()
    }

    @Singleton
    @Provides
    fun providesRetrofit(
        gson: Gson
    ): Retrofit.Builder {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        httpClient.addInterceptor(BasicAuthInterceptor())
        return Retrofit.Builder()
            .baseUrl("https://ext.digio.in:444")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())

    }


    @Singleton
    @Provides
    fun providesDocumentRetrofitCall(
        retrofit: Retrofit.Builder
    ): DocumentRetrofitApiCalls {
        return retrofit.build().create(DocumentRetrofitApiCalls::class.java)
    }
}