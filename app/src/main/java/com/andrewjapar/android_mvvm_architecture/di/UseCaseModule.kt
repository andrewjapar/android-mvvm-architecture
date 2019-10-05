package com.andrewjapar.android_mvvm_architecture.di

import com.andrewjapar.android_mvvm_architecture.domain.usecase.GetUserList
import com.andrewjapar.android_mvvm_architecture.domain.usecase.GetUserListImpl
import com.andrewjapar.android_mvvm_architecture.repository.UserRepository
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideGetUserList(userRepository: UserRepository): GetUserList {
        return GetUserListImpl(userRepository)
    }
}