package com.andrewjapar.android_mvvm_architecture.external.responses

import com.andrewjapar.android_mvvm_architecture.domain.entity.UserInfo
import com.andrewjapar.android_mvvm_architecture.internal.responses.LocalUserResponse
import com.andrewjapar.android_mvvm_architecture.utils.AvatarRandomizer

data class RemoteUserResponse(
    val name: String,
    val email: String,
    val address: Address,
    val website: String,
    val company: Company
)

data class Geolocation(val lat: Double, val lng: Double)
data class Address(
    val street: String,
    val geo: Geolocation
)

data class Company(
    val catchPhrase: String
)

fun List<RemoteUserResponse>.mapToUserInfo(avatarRandomizer: AvatarRandomizer): List<UserInfo> {
    return this.map { user ->
        UserInfo(
            user.name,
            avatarRandomizer.get(),
            user.website,
            user.company.catchPhrase
        )
    }
}

fun List<RemoteUserResponse>.mapToLocalUserResponse(avatarRandomizer: AvatarRandomizer): List<LocalUserResponse> {
    return this.map { user ->
        LocalUserResponse(
            0,
            user.name,
            avatarRandomizer.get().toString(),
            user.website,
            user.company.catchPhrase
        )
    }
}