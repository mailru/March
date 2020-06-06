package ru.mail.march.channel

interface DataChannelFactory {
    fun <T> createStateChannel(comparator: StateComparator<T> = SimpleStateComparator()): DataChannel<T>
    fun <T> createEventChannel(): DataChannel<T>
}