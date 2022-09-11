package com.astro.test.muadzabdurrahman.feature.main.repository

import com.astro.test.muadzabdurrahman.data.entity.User
import com.astro.test.muadzabdurrahman.data.event.MutableStateEventManager
import com.astro.test.muadzabdurrahman.data.event.StateEventManager
import com.astro.test.muadzabdurrahman.utils.fetchStateEventSubscriber
import io.reactivex.disposables.CompositeDisposable
import okhttp3.internal.closeQuietly

class SearchRepositoryImpl(private val dataSource: SearchDataSource) : SearchRepository {

    private val disposables = CompositeDisposable()

    private var _searchStateEventManager: MutableStateEventManager<List<User>> = MutableStateEventManager()
    override val searchStateEventManager: StateEventManager<List<User>>
        get() = _searchStateEventManager

    override fun search(keyword: String, page: Int) {
        val disposable = dataSource.searchUser(keyword, page).fetchStateEventSubscriber {
            _searchStateEventManager.post(it)
        }
        disposables.add(disposable)
    }

    override fun close() {
        _searchStateEventManager.closeQuietly()
        disposables.dispose()
    }
}