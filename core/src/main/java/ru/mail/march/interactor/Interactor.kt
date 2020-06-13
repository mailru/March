package ru.mail.march.interactor

import ru.mail.march.channel.*

abstract class Interactor {
    val channelFactory = DataChannelFactoryWrapper()

    open fun create() {}
    open fun destroy() {}

    fun attachChannelFactory(factory: DataChannelFactory) {
        channelFactory.attachChannelFactory(factory)
    }
}