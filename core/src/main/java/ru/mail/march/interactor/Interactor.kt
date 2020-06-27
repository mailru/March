package ru.mail.march.interactor

import ru.mail.march.channel.DataChannel
import ru.mail.march.channel.DataChannelFactory
import ru.mail.march.channel.LazyDataChannelFactory
import ru.mail.march.channel.StateComparator

abstract class Interactor : DataChannelFactory {
    private val channelFactory = LazyDataChannelFactory()

    open fun create() {}
    open fun destroy() {}

    fun attachChannelFactory(factory: DataChannelFactory) {
        channelFactory.attachChannelFactory(factory)
    }

    override fun <T> createStateChannel(comparator: StateComparator<T>): DataChannel<T> {
        return channelFactory.createStateChannel(comparator)
    }

    override fun <T> createEventChannel(): DataChannel<T> {
        return channelFactory.createEventChannel()
    }
}