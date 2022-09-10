package com.astro.test.muadzabdurrahman.feature.main.repository

import com.astro.test.muadzabdurrahman.data.entity.User
import com.astro.test.muadzabdurrahman.data.remote.response.SearchUserResponse
import com.astro.test.muadzabdurrahman.utils.orZero

object UserMapper {

    fun mapResponseToEntity(response: SearchUserResponse): List<User> {
        val userList : MutableList<User> = mutableListOf()
        if (!response.items.isNullOrEmpty()) {
            for (item in response.items) {
                userList.add(
                    User(
                        id = item.id.orZero(),
                        name = item.login.orEmpty(),
                        avatarUrl = item.avatarUrl.orEmpty()
                    )
                )
            }
        }
        return userList
    }

}