package ru.mail.march.channel

internal class LazyDataChannelFactory : DataChannelFactory {
    private val channels = ArrayList<Initializer<*>>()

    override fun <T> createStateChannel(comparator: StateComparator<T>): DataChannel<T> {
        val element = StateChannel(comparator)
        channels.add(element)
        return element
    }

    override fun <T> createEventChannel(): DataChannel<T> {
        val element = EventChannel<T>()
        channels.add(element)
        return element
    }

    fun attachChannelFactory(factory: DataChannelFactory) {
        channels.forEach {
            it.init(factory)
        }
    }
}
