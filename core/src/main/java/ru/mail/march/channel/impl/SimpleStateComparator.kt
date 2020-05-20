package ru.mail.march.channel.impl

import ru.mail.march.channel.StateComparator

class SimpleStateComparator<T> : StateComparator<T> {
    override fun equals(o1: T, o2: T): Boolean {
        return o1 == o2
    }
}