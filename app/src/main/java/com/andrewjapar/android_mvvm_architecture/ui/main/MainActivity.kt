package com.andrewjapar.android_mvvm_architecture.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.andrewjapar.android_mvvm_architecture.R
import com.andrewjapar.android_mvvm_architecture.di.ViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val userListAdapter = MainAdapter()

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

        initializedUserList()

        mainViewModel.loadUsers()
        mainViewModel.users.observe(this, Observer {
            userListAdapter.data = it
            userListAdapter.notifyDataSetChanged()
        })
    }

    private fun initializedUserList() {
        userList.adapter = userListAdapter
        userList.layoutManager = LinearLayoutManager(this)
        userList.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }
}
