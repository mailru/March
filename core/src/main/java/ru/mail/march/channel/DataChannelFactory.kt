package ru.mail.march.channel

import ru.mail.march.channel.impl.SimpleStateComparator

interface DataChannelFactory {
    fun <T> createStateChannel(comparator: StateComparator<T> = SimpleStateComparator()): DataChannel<T>
    fun <T> createEventChannel(): DataChannel<T>
}