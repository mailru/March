package ru.mail.march

import androidx.lifecycle.LifecycleOwner
import ru.mail.march.channel.*

class TestDataChannelFactory(private val owner: LifecycleOwner) :
    DataChannelFactory {

    private val channels = ArrayList<LiveDataChannel<*>>()

    override fun <T> createStateChannel(comparator: StateComparator<T>): DataChannel<T> {
        return StateDataChannel(comparator).also {
            it.attachOwner(owner)
            channels.add(it)
        }
    }

    override fun <T> createEventChannel(): DataChannel<T> {
        return EventDataChannel<T>().also {
            it.attachOwner(owner)
            channels.add(it)
        }
    }
}