package ru.mail.march.channel

class DataChannelFactoryWrapper : DataChannelFactory {
    val channels = ArrayList<Initializer<*>>()

    override fun <T> createStateChannel(comparator: StateComparator<T>): DataChannel<T> {
        val element = StateDelegate(comparator)
        channels.add(element)
        return element
    }

    override fun <T> createEventChannel(): DataChannel<T> {
        val element = EventDelegate<T>()
        channels.add(element)
        return element
    }

    fun attachChannelFactory(factory: DataChannelFactory) {
        channels.forEach {
            it.init(factory)
        }
    }
}


open class DataChannelWrapper<T> : DataChannel<T> {
    var channel: DataChannel<T> = Stub()

    override fun postValue(value: T) {
        channel.postValue(value)
    }

    override fun observe(observer: DataChannel.Observer<T>) {
        channel.observe(observer)
    }

    override fun observe(observer: (T) -> Unit) {
        channel.observe(observer)
    }

    override fun getValue(): T? {
        return channel.getValue()
    }
}

class EventDelegate<T> : DataChannelWrapper<T>(), Initializer<DataChannel<T>> {
    override fun init(factory: DataChannelFactory) {
        channel = factory.createEventChannel()
    }
}

class StateDelegate<T>(
    private val comparator: StateComparator<T> = SimpleStateComparator()
) : DataChannelWrapper<T>(), Initializer<DataChannel<T>> {

    override fun init(factory: DataChannelFactory) {
        channel = factory.createStateChannel(comparator)
    }
}

interface Initializer<T> {
    fun init(factory: DataChannelFactory)
}

internal class Stub<T> : DataChannel<T> {
    override fun postValue(value: T) {
        throw IllegalAccessException()
    }

    override fun observe(observer: DataChannel.Observer<T>) {
        throw IllegalAccessException()
    }

    override fun observe(observer: (T) -> Unit) {
        throw IllegalAccessException()
    }

    override fun getValue(): T? {
        throw IllegalAccessException()
    }
}