package com.stevemd.data.di

import com.stevemd.data.githubapiservice.ApiService
import com.stevemd.di.qualifier.AppBaseUrl
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

object ApiServiceModule {
    @Provides
    @Singleton
    fun provideApiService(
        @AppBaseUrl
        retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}