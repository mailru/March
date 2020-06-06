package ru.mail.march.channel

import androidx.lifecycle.LifecycleOwner

class LiveDataChannelFactory : DataChannelFactory {
    private val channels = ArrayList<LiveDataChannel<*>>()

    override fun <T> createStateChannel(comparator: StateComparator<T>): DataChannel<T> {
        return StateDataChannel(comparator).also {
            channels.add(it)
        }
    }

    override fun <T> createEventChannel(): DataChannel<T> {
        return EventDataChannel<T>().also {
            channels.add(it)
        }
    }

    fun attachOwner(owner: LifecycleOwner) {
        channels.forEach {
            it.attachOwner(owner)
        }
    }
}