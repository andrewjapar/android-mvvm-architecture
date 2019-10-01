package com.andrewjapar.android_mvvm_architecture.internal.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andrewjapar.android_mvvm_architecture.internal.responses.LocalUserResponse
import io.reactivex.Single

@Dao
interface UserDao {

    @Query("SELECT * from user")
    fun getUsers(): Single<List<LocalUserResponse>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: LocalUserResponse): Single<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: List<LocalUserResponse>): Single<List<Long>>
}