package ru.mail.march.channel

internal class LazyEventChannel<T> : LazyDataChannel<T>() {
    override fun attachFactory(factory: DataChannelFactory) {
        channel = factory.createEventChannel()
    }
}