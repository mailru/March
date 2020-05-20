package ru.mail.march.channel

interface StateComparator<T> {
    fun equals(o1: T, o2: T): Boolean
}