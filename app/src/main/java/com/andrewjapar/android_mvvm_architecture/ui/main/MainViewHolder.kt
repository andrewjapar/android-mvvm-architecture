package com.andrewjapar.android_mvvm_architecture.ui.main

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.andrewjapar.android_mvvm_architecture.domain.entity.UserInfo
import com.andrewjapar.android_mvvm_architecture.utils.ImageViewUtil
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.item_user.view.*

class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val useName by lazy { itemView.userName }
    private val companyTagline by lazy { itemView.companyTagline }
    private val userWebsite by lazy { itemView.userWebsite }
    private val userAvatar by lazy { itemView.userAvatar }

    fun onBind(item: UserInfo, actionListener: PublishSubject<UserInfo>) {

        ImageViewUtil.loadImage(itemView.context, item.avatarUrl.toString(), true)?.into(userAvatar)

        useName.text = item.name
        companyTagline.text = item.roleDescription
        userWebsite.text = item.domainName

        itemView.setOnClickListener {
            actionListener.onNext(item)
        }
    }
}