package ru.mail.march.channel

internal abstract class LazyDataChannel<T> : DataChannel<T> {
    var channel: DataChannel<T> = StubDataChannel()

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

    abstract fun attachFactory(factory: DataChannelFactory)
}