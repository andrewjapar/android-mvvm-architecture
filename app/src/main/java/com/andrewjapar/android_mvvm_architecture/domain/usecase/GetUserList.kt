package com.andrewjapar.android_mvvm_architecture.domain.usecase

import com.andrewjapar.android_mvvm_architecture.domain.entity.UserInfo
import com.andrewjapar.android_mvvm_architecture.repository.UserRepository
import io.reactivex.Single

interface GetUserList {
    fun execute(): Single<List<UserInfo>>
}

class GetUserListImpl(private val userRepository: UserRepository) : GetUserList {

    override fun execute(): Single<List<UserInfo>> {
        return userRepository.getUsers()
    }
}