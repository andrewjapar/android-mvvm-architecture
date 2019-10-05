package com.andrewjapar.android_mvvm_architecture

import com.andrewjapar.android_mvvm_architecture.di.DaggerAppComponent
import com.andrewjapar.android_mvvm_architecture.di.NetworkModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class ArchApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val networkModule = NetworkModule(baseUrl, 30_000)

        return DaggerAppComponent.builder()
            .application(this)
            .networkModule(networkModule)
            .build()
    }

    companion object {
        var baseUrl = BuildConfig.BASE_URL
    }
}