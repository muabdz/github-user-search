package com.astro.test.muadzabdurrahman.feature.main.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.astro.test.muadzabdurrahman.R
import com.astro.test.muadzabdurrahman.data.entity.User
import com.astro.test.muadzabdurrahman.databinding.ItemUserListBinding

class SearchUserAdapter(private val userList : List<User>, private val listener: UserListener): RecyclerView.Adapter<SearchUserAdapter.UserViewHolder>() {

    fun addUsers(userList: List<User>?) {
        if (userList == null) return
        this.userList.toMutableList().addAll(userList)
        notifyItemRangeInserted(this.userList.size, userList.size)
    }

    inner class UserViewHolder(private val viewBinding: ItemUserListBinding): RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(item: User) {
            with(viewBinding) {
                tvName.text = item.name
                ibFavorite.setImageResource(
                    if (item.favorite) {
                        R.drawable.ic_heart_filled
                    } else {
                        R.drawable.ic_heart_outline
                    }
                )
                ibFavorite.setOnClickListener {
                    listener.onFavoriteClick(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemUserListBinding = ItemUserListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(itemUserListBinding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int = userList.size

    interface UserListener {
       fun onFavoriteClick(user: User)
    }
}