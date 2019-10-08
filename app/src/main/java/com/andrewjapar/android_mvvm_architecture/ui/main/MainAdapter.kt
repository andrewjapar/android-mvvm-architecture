package com.andrewjapar.android_mvvm_architecture.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andrewjapar.android_mvvm_architecture.R
import com.andrewjapar.android_mvvm_architecture.domain.entity.UserInfo
import io.reactivex.subjects.PublishSubject

class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {

    var data: List<UserInfo> = emptyList()
    val itemListener = PublishSubject.create<UserInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_user,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.onBind(data[position], itemListener)
    }
}