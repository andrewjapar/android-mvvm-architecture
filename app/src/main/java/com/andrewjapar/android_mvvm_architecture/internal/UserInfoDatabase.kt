package com.andrewjapar.android_mvvm_architecture.internal

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.andrewjapar.android_mvvm_architecture.internal.dao.UserDao
import com.andrewjapar.android_mvvm_architecture.internal.responses.LocalUserResponse

@Database(entities = [LocalUserResponse::class], version = 1)
abstract class UserInfoDatabase : RoomDatabase() {

    abstract fun authenticationDao(): UserDao

    companion object {

        private var INSTANCE: UserInfoDatabase? = null

        private val sLock = Any()

        fun getInstance(context: Context): UserInfoDatabase {
            synchronized(sLock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        UserInfoDatabase::class.java, "userinfo.db"
                    )
                        .build()
                }
                return INSTANCE!!
            }
        }
    }

}