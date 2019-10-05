package com.andrewjapar.android_mvvm_architecture.di

import com.andrewjapar.android_mvvm_architecture.ArchApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        AppModule::class,
        AndroidSupportInjectionModule::class,
        ActivityBuilder::class,
        NetworkModule::class,
        LocalDataModule::class,
        ApiModule::class,
        RepositoryModule::class,
        UseCaseModule::class,
        ViewModelModule::class
    ]
)
@Singleton
interface AppComponent : AndroidInjector<ArchApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: ArchApplication): Builder

        fun networkModule(networkModule: NetworkModule): Builder

        fun build(): AppComponent
    }
}