package com.andrewjapar.android_mvvm_architecture.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.andrewjapar.android_mvvm_architecture.domain.entity.UserInfo
import com.andrewjapar.android_mvvm_architecture.domain.usecase.GetUserList
import com.andrewjapar.android_mvvm_architecture.domain.usecase.GetUserListImpl
import com.andrewjapar.android_mvvm_architecture.repository.UserRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.net.URL

class MainViewModelTest {

    private lateinit var userRepository: UserRepository
    private lateinit var mainViewModel: MainViewModel
    private lateinit var getUserList: GetUserList

    private lateinit var observer: Observer<List<UserInfo>>

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        userRepository = mock()
        observer = mock()
        getUserList = GetUserListImpl(userRepository)
        mainViewModel = MainViewModel(getUserList)
    }

    @After
    fun tearDown() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }

    @Test
    fun `when user is successfully fetched, then should return users`() {
        whenever(userRepository.getUsers()).thenReturn(Single.just(listOf(buildDummyUser())))
        mainViewModel.users.observeForever(observer)

        mainViewModel.loadUsers()

        verify(observer).onChanged(listOf(buildDummyUser()))
    }

    private fun buildDummyUser(): UserInfo {
        return UserInfo(
            "nama",
            URL("https://getaway.id"),
            "https://getaway.id",
            "Software Engineer"
        )
    }

}