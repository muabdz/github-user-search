package com.astro.test.muadzabdurrahman.feature.main.repository

import com.astro.test.muadzabdurrahman.data.entity.User
import com.astro.test.muadzabdurrahman.data.remote.ApiServices
import com.astro.test.muadzabdurrahman.utils.mapObservable
import io.reactivex.Observable

class SearchDataSource(private val apiServices: ApiServices) {

    fun searchUser(keyword: String, page: Int): Observable<List<User>> {
        return apiServices.searchUsers(keyword, page).mapObservable { response ->
            UserMapper.mapResponseToEntity(response)
        }
    }
}