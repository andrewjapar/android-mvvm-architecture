package com.andrewjapar.android_mvvm_architecture.di

import android.content.Context
import com.andrewjapar.android_mvvm_architecture.ArchApplication
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

@Module
class AppModule {

    @Provides
    fun provideContext(application: ArchApplication): Context {
        return application
    }

    @Provides
    fun provideIOSchedulers(): Scheduler {
        return Schedulers.io()
    }
}