package com.andrewjapar.android_mvvm_architecture.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andrewjapar.android_mvvm_architecture.domain.entity.UserInfo
import com.andrewjapar.android_mvvm_architecture.domain.usecase.GetUserList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getUserList: GetUserList
) : ViewModel() {

    private val disposeBag = CompositeDisposable()

    private val _users = MutableLiveData<List<UserInfo>>()
    val users: LiveData<List<UserInfo>> = _users

    fun loadUsers() {
        getUserList.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _users.value = it
            }, {

            })
            .let { disposeBag.add(it) }
    }
}