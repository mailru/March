package ru.mail.march.channel

internal class StateChannel<T>(
    private val comparator: StateComparator<T> = SimpleStateComparator()
) : LazyDataChannel<T>() {
    override fun attachFactory(factory: DataChannelFactory) {
        channel = factory.createStateChannel(comparator)
    }
}