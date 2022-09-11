package com.astro.test.muadzabdurrahman.feature.main.ui

import androidx.lifecycle.ViewModel
import com.astro.test.muadzabdurrahman.data.entity.User
import com.astro.test.muadzabdurrahman.data.event.StateEventManager
import com.astro.test.muadzabdurrahman.data.remote.ApiServices
import com.astro.test.muadzabdurrahman.feature.main.repository.SearchDataSource
import com.astro.test.muadzabdurrahman.feature.main.repository.SearchRepository
import com.astro.test.muadzabdurrahman.feature.main.repository.SearchRepositoryImpl

class MainViewModel : ViewModel() {
    private val service = ApiServices.create()
    private val dataSource = SearchDataSource(service)
    private val repository: SearchRepository = SearchRepositoryImpl(dataSource)

    var keyword: String = ""
    var page: Int = 1
    private var _userList : MutableList<User> = mutableListOf()
    var userList: List<User> get() = _userList
        set(value) {
            _userList = value.toMutableList()
        }

    fun addUserList(users : List<User>) {
        _userList.addAll(users)
    }

    val searchEventManager: StateEventManager<List<User>> = repository.searchStateEventManager

    fun searchUser() {
        repository.search(keyword, page)
    }
}