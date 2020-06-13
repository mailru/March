package ru.mail.march.channel

internal class EventChannel<T> : DataChannelWrapper<T>(),
    Initializer<DataChannel<T>> {
    override fun init(factory: DataChannelFactory) {
        channel = factory.createEventChannel()
    }
}