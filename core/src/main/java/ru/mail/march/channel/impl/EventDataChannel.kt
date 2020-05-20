package ru.mail.march.channel.impl

import java.util.concurrent.atomic.AtomicBoolean

class EventDataChannel<T> : LiveDataChannel<T>() {
    private val unpostedEvent = AtomicBoolean()

    override fun postValue(value: T) {
        unpostedEvent.set(true)
        super.postValue(value)
    }

    override fun observe(observer: (T) -> Unit) {
        super.observe { value ->
            if (unpostedEvent.getAndSet(false)) {
                observer(value)
            }
        }
    }
}