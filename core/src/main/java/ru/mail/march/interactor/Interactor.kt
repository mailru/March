package ru.mail.march.interactor

import ru.mail.march.channel.DataChannelFactory
import ru.mail.march.channel.LazyDataChannelFactory

abstract class Interactor {
    private val channelFactory = LazyDataChannelFactory()

    open fun create() {}
    open fun destroy() {}

    fun attachChannelFactory(factory: DataChannelFactory) {
        channelFactory.attachChannelFactory(factory)
    }
}