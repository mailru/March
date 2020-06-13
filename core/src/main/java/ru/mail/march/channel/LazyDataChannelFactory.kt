package ru.mail.march.channel

class LazyDataChannelFactory : DataChannelFactory {
    private val channels = ArrayList<LazyDataChannel<*>>()

    override fun <T> createStateChannel(comparator: StateComparator<T>): DataChannel<T> {
        val element = StateChannel(comparator)
        channels.add(element)
        return element
    }

    override fun <T> createEventChannel(): DataChannel<T> {
        val element = LazyEventChannel<T>()
        channels.add(element)
        return element
    }

    fun attachChannelFactory(factory: DataChannelFactory) {
        channels.forEach {
            it.attachFactory(factory)
        }
    }
}
