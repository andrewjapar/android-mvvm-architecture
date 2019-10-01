package com.andrewjapar.android_mvvm_architecture.internal.responses

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.andrewjapar.android_mvvm_architecture.domain.entity.UserInfo
import java.net.URL

@Entity(tableName = "user")
data class LocalUserResponse(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "mId")
    val mId: Int = 0,

    @ColumnInfo(name = "mName")
    val mName: String,

    @ColumnInfo(name = "mAvatarUrl")
    val mAvatarUrl: String,

    @ColumnInfo(name = "mDomainName")
    val mDomainName: String,

    @ColumnInfo(name = "mRoleDescription")
    val mRoleDescription: String
)

fun List<LocalUserResponse>.mapToUserInfo(): List<UserInfo> {
    return this.map {
        UserInfo(it.mName, URL(it.mAvatarUrl), it.mDomainName, it.mRoleDescription)
    }
}