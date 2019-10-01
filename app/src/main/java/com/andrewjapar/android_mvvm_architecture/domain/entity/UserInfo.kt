package com.andrewjapar.android_mvvm_architecture.domain.entity

import java.net.URL

data class UserInfo(
    val name: String,
    val avatarUrl: URL,
    val domainName: String,
    val roleDescription: String
)