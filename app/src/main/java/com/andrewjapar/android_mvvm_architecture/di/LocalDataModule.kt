package com.andrewjapar.android_mvvm_architecture.di

import android.content.Context
import com.andrewjapar.android_mvvm_architecture.internal.ArchDatabase
import dagger.Module
import dagger.Provides

@Module
class LocalDataModule {

    @Provides
    fun provideArchDatabase(context: Context): ArchDatabase {
        return ArchDatabase.getInstance(context)
    }
}