package com.andrewjapar.android_mvvm_architecture.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andrewjapar.android_mvvm_architecture.domain.entity.UserInfo
import com.andrewjapar.android_mvvm_architecture.repository.UserRepository
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(
    private val userRepository: UserRepository,
    private val ioThread: Scheduler = Schedulers.io()
) : ViewModel() {

    private val disposeBag = CompositeDisposable()

    private val _users = MutableLiveData<List<UserInfo>>()
    val users: LiveData<List<UserInfo>> = _users

    fun loadUsers() {
        userRepository.getUsers()
            .subscribeOn(ioThread)
            .subscribe({
                _users.value = it
            }, {

            })
            .let { disposeBag.add(it) }
    }
}