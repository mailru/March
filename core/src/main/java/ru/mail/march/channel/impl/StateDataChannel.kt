package ru.mail.march.channel.impl

import ru.mail.march.channel.StateComparator

class StateDataChannel<T>(private val comparator: StateComparator<T>) : LiveDataChannel<T>() {
    private var cache : T? = null
    private val lock = Any()

    override fun postValue(value: T) {
        var newValue : T? = null

        synchronized(lock) {
            val previousValue = cache
            if (previousValue == null || !comparator.equals(value, previousValue)) {
                cache = value
                newValue = value
            }
        }

        if (newValue != null) {
            super.postValue(value)
        }
    }
}