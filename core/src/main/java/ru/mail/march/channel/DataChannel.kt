package ru.mail.march.channel

interface DataChannel<T> {
    fun postValue(value: T)
    fun observe(observer: Observer<T>)
    fun observe(observer: (T) -> Unit)
    fun getValue(): T?

    interface Observer<T> {
        fun onChanged(value: T?)
    }
}

operator fun <T> DataChannel<T>.invoke(observer: (T) -> Unit) {
    this.observe(observer)
}