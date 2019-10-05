package com.andrewjapar.android_mvvm_architecture.di

import com.andrewjapar.android_mvvm_architecture.external.api.UserApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ApiModule {

    @Provides
    fun provideUserApi(retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }
}