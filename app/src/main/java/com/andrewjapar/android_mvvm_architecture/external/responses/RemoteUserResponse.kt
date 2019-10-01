package com.andrewjapar.android_mvvm_architecture.external.responses

import com.andrewjapar.android_mvvm_architecture.domain.entity.UserInfo
import com.andrewjapar.android_mvvm_architecture.internal.responses.LocalUserResponse
import com.andrewjapar.android_mvvm_architecture.utils.AvatarRandomizerImpl

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

fun List<RemoteUserResponse>.mapToUserInfo(): List<UserInfo> {
    return this.map { user ->
        UserInfo(
            user.name,
            AvatarRandomizerImpl().get(),
            user.website,
            user.company.catchPhrase
        )
    }
}

fun List<RemoteUserResponse>.mapToLocalUserResponse(): List<LocalUserResponse> {
    return this.map { user ->
        LocalUserResponse(
            0,
            user.name,
            AvatarRandomizerImpl().get().toString(),
            user.website,
            user.company.catchPhrase
        )
    }
}