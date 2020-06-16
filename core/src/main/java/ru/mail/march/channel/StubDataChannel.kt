package ru.mail.march.channel

internal class StubDataChannel<T> : DataChannel<T> {
    override fun postValue(value: T) {
        throw IllegalAccessException()
    }

    override fun observe(observer: DataChannel.Observer<T>) {
        throw IllegalAccessException()
    }

    override fun observe(observer: (T) -> Unit) {
        throw IllegalAccessException()
    }

    override fun getValue(): T? {
        throw IllegalAccessException()
    }
}