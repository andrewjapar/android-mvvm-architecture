package com.andrewjapar.android_mvvm_architecture.repository

import com.andrewjapar.android_mvvm_architecture.domain.entity.UserInfo
import com.andrewjapar.android_mvvm_architecture.external.api.UserApi
import com.andrewjapar.android_mvvm_architecture.external.responses.mapToLocalUserResponse
import com.andrewjapar.android_mvvm_architecture.external.responses.mapToUserInfo
import com.andrewjapar.android_mvvm_architecture.internal.dao.UserDao
import com.andrewjapar.android_mvvm_architecture.internal.responses.mapToUserInfo
import com.andrewjapar.android_mvvm_architecture.utils.AvatarRandomizer
import io.reactivex.Single

interface UserRepository {
    fun getUsers(): Single<List<UserInfo>>
}

class UserRepositoryImpl(
    private val userApi: UserApi,
    private val userDao: UserDao,
    private val avatarRandomizer: AvatarRandomizer
) : UserRepository {

    override fun getUsers(): Single<List<UserInfo>> {
        return userDao.getUsers()
            .map { it.mapToUserInfo() }
            .flatMap { users ->
                if (users.isEmpty()) fetchAndInsertDataFromApi()
                else Single.just(users)
            }
    }

    private fun fetchAndInsertDataFromApi(): Single<List<UserInfo>> {
        return userApi.getUsers()
            .doOnSuccess { userDao.insertUser(it.mapToLocalUserResponse(avatarRandomizer)) }
            .map { it.mapToUserInfo(avatarRandomizer) }
    }
}