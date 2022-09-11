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

    val searchEventManager: StateEventManager<List<User>> = repository.searchStateEventManager

    fun searchUser(keyword: String, page: Int) {
        repository.search(keyword, page)
    }
}