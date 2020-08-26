package ru.mail.march.interactor

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.mail.march.channel.*
import kotlin.coroutines.EmptyCoroutineContext

abstract class Interactor {
    private val channelFactory = LazyDataChannelFactory()

    var interactorScope = CoroutineScope(EmptyCoroutineContext)

    open fun create() {}
    open fun destroy() {}

    fun attachChannelFactory(factory: DataChannelFactory) {
        channelFactory.attachChannelFactory(factory)
    }

    fun <T> stateChannel(comparator: StateComparator<T> = SimpleStateComparator()): DataChannel<T> {
        return channelFactory.createStateChannel(comparator)
    }

    fun <T> eventChannel(): DataChannel<T> {
        return channelFactory.createEventChannel()
    }

    fun execute(block: suspend CoroutineScope.() -> Unit) {
        interactorScope.launch {
            withContext(Dispatchers.IO) {
                block.invoke(this)
            }
        }
    }
}