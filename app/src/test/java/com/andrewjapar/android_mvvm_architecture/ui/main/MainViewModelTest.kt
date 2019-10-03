package com.andrewjapar.android_mvvm_architecture.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.andrewjapar.android_mvvm_architecture.domain.entity.UserInfo
import com.andrewjapar.android_mvvm_architecture.repository.UserRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.net.URL

class MainViewModelTest {

    private lateinit var userRepository: UserRepository
    private lateinit var mainViewModel: MainViewModel

    private lateinit var observer: Observer<List<UserInfo>>

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        userRepository = mock()
        observer = mock()
        mainViewModel = MainViewModel(userRepository, Schedulers.trampoline())
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