package ru.mail.march.interactor

import ru.mail.march.channel.*

abstract class Interactor {
    private val channelFactory = LazyDataChannelFactory()

    open fun create() {}
    open fun destroy() {}

    fun attachChannelFactory(factory: DataChannelFactory) {
        channelFactory.attachChannelFactory(factory)
    }

    fun <T> stateChannel(comparator: StateComparator<T> = SimpleStateComparator()): DataChannel<T> {
        return channelFactory.createStateChannel(comparator)
    }

    fun <T> eventChannel(): DataChannel<T> {
        return channelFactory.createEventChannel()
    }
}