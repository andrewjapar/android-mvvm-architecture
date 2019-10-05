package com.andrewjapar.android_mvvm_architecture.di

import com.andrewjapar.android_mvvm_architecture.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
}