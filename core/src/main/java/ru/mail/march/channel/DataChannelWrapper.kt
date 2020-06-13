package ru.mail.march.channel

internal open class DataChannelWrapper<T> : DataChannel<T> {
    var channel: DataChannel<T> = DataChannelStub()

    override fun postValue(value: T) {
        channel.postValue(value)
    }

    override fun observe(observer: DataChannel.Observer<T>) {
        channel.observe(observer)
    }

    override fun observe(observer: (T) -> Unit) {
        channel.observe(observer)
    }

    override fun getValue(): T? {
        return channel.getValue()
    }
}