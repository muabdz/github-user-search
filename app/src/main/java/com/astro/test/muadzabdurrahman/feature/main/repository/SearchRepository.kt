package com.astro.test.muadzabdurrahman.feature.main.repository

import com.astro.test.muadzabdurrahman.data.entity.User
import com.astro.test.muadzabdurrahman.data.event.StateEventManager
import java.io.Closeable

interface SearchRepository : Closeable {
    val searchStateEventManager: StateEventManager<List<User>>

    fun search(keyword: String, page: Int)
}