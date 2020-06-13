package ru.mail.march.channel

internal interface Initializer<T> {
    fun init(factory: DataChannelFactory)
}