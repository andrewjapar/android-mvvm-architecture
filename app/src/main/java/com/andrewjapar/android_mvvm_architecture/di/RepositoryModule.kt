package com.andrewjapar.android_mvvm_architecture.di

import com.andrewjapar.android_mvvm_architecture.external.api.UserApi
import com.andrewjapar.android_mvvm_architecture.internal.ArchDatabase
import com.andrewjapar.android_mvvm_architecture.repository.UserRepository
import com.andrewjapar.android_mvvm_architecture.repository.UserRepositoryImpl
import com.andrewjapar.android_mvvm_architecture.utils.AvatarRandomizerImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideUserRepository(userApi: UserApi, archDatabase: ArchDatabase): UserRepository {
        return UserRepositoryImpl(userApi, archDatabase.userDao(), AvatarRandomizerImpl())
    }
}