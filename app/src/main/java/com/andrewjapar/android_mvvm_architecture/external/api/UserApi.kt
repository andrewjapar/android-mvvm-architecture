package com.andrewjapar.android_mvvm_architecture.external.api

import com.andrewjapar.android_mvvm_architecture.external.responses.RemoteUserResponse
import io.reactivex.Single
import retrofit2.http.GET


interface UserApi {

    @GET("users")
    fun getUsers(): Single<List<RemoteUserResponse>>
}