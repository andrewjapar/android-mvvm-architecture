package com.andrewjapar.android_mvvm_architecture.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.andrewjapar.android_mvvm_architecture.ArchApplication
import com.andrewjapar.android_mvvm_architecture.R
import com.andrewjapar.android_mvvm_architecture.di.ViewModelFactory
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val mainViewModel by lazy {
        ViewModelProviders.of(
            this,
            viewModelFactory
        )[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.loadUsers()

        mainViewModel.users.observe(this, Observer { Log.d("test", "user: $it") })
    }
}
