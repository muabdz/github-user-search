package com.astro.test.muadzabdurrahman.data.event

import io.reactivex.subjects.PublishSubject
import java.io.Closeable

abstract class StateEventManager<T: Any> : Closeable {
    protected abstract val stateEventSubject: PublishSubject<StateEvent<T>>

    abstract var onLoading: () -> Unit
    abstract var onSuccess: (data: T) -> Unit
    abstract var onFailure: (exception: Throwable) -> Unit
}