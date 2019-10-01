package com.andrewjapar.android_mvvm_architecture.repository

import com.andrewjapar.android_mvvm_architecture.external.api.UserApi
import com.andrewjapar.android_mvvm_architecture.external.responses.*
import com.andrewjapar.android_mvvm_architecture.internal.dao.UserDao
import com.andrewjapar.android_mvvm_architecture.utils.AvatarRandomizer
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.net.URL

class UserRepositoryImplTest {

    private lateinit var userDao: UserDao
    private lateinit var userApi: UserApi
    private lateinit var avatarRandomizer: AvatarRandomizer

    private lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        userApi = mock()
        userDao = mock()
        avatarRandomizer = mock()
        userRepository = UserRepositoryImpl(userApi, userDao, avatarRandomizer)
    }

    @Test
    fun `given fetch data from local is empty, when getUsers, then should call remote api and insert to local db`() {
        val remoteUserResponse = listOf(buildDummyRemoteUser())

        whenever(userDao.getUsers()).thenReturn(Single.just(emptyList()))
        whenever(userApi.getUsers()).thenReturn(Single.just(remoteUserResponse))
        whenever(avatarRandomizer.get()).thenReturn(URL("https://vidio.com"))

        val actualResult = userRepository.getUsers().blockingGet()
        val expectedResult = remoteUserResponse.mapToUserInfo(avatarRandomizer)

        verify(userDao).insertUser(remoteUserResponse.mapToLocalUserResponse(avatarRandomizer))
        assertEquals(expectedResult, actualResult)
    }

    private fun buildDummyRemoteUser(): RemoteUserResponse {
        return RemoteUserResponse(
            "mantap",
            "mantap@gmail.com",
            Address("mantap", Geolocation(12.0, 12.0)),
            "https://manta.com",
            Company("mantap")
        )
    }
}