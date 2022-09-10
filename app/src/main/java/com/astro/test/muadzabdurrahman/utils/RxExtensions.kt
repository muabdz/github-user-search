package com.astro.test.muadzabdurrahman.utils

import com.astro.test.muadzabdurrahman.data.event.StateEvent
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

fun <T: Any, U: Any> Observable<Response<T>>.mapObservable(mapper: (T) -> U): Observable<U> {
    return flatMap { response ->
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                val dataMapper = mapper.invoke(body)
                Observable.just(dataMapper)
            } else {
                val exception = Throwable("Response data is null, message: ${response.message()}")
                Observable.error(exception)
            }
        } else {
            val messageHttp = response.message()
            val exception = Throwable(messageHttp)
            Observable.error(exception)
        }
    }
}

fun <T: Any> Observable<T>.fetchStateEventSubscriber(onSubscribe: (StateEvent<T>) -> Unit) : Disposable {
    return subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe {
            val eventLoading = StateEvent.Loading<T>()
            onSubscribe.invoke(eventLoading)
        }
        .subscribe({ data ->
            val eventSuccess = StateEvent.Success<T>(data)
            onSubscribe.invoke(eventSuccess)
        }, { throwable ->
            val eventFailure = StateEvent.Failure<T>(throwable)
            onSubscribe.invoke(eventFailure)
        })
}