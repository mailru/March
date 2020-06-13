package ru.mail.march.channel

internal class StateChannel<T>(
    private val comparator: StateComparator<T> = SimpleStateComparator()
) : DataChannelWrapper<T>(),
    Initializer<DataChannel<T>> {

    override fun init(factory: DataChannelFactory) {
        channel = factory.createStateChannel(comparator)
    }
}