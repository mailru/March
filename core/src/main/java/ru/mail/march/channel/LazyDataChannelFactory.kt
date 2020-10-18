package ru.mail.march.channel

class LazyDataChannelFactory : DataChannelFactory {
    private val channels = ArrayList<LazyDataChannel<*>>()
    private var channelFactory: DataChannelFactory? = null

    override fun <T> createStateChannel(comparator: StateComparator<T>): DataChannel<T> {
        channelFactory?.let {
            return it.createStateChannel(comparator)
        }
        val element = StateChannel(comparator)
        channels.add(element)
        return element
    }

    override fun <T> createEventChannel(): DataChannel<T> {
        channelFactory?.let {
            return it.createEventChannel()
        }
        val element = LazyEventChannel<T>()
        channels.add(element)
        return element
    }

    fun attachChannelFactory(factory: DataChannelFactory) {
        channelFactory = factory
        channels.forEach {
            it.attachFactory(factory)
        }
    }
}
