package ru.mail.march

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import ru.mail.march.channel.DataChannel
import ru.mail.march.channel.impl.LiveDataChannelFactory

class EventUnitTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val observer: Observer<String> = mock()

    @Test
    /**
     * гарантирует, что подписчик получит все события, не только уникальные
     */
    fun eventChannelTest1() {
        val channelEvent = getEventChannelForTest<String>()

        channelEvent.observe {
            observer.onChanged(it)
        }

        channelEvent.postValue("event")

        assertEquals("event", channelEvent.getValue())

        verify(observer).onChanged("event")

        channelEvent.postValue("event")

        verify(observer, times(2)).onChanged("event")
    }

    @Test
    /**
     * при подписке получатель будет оповещен последними непрочитанным из канала событием
     */
    fun eventChannelTest2() {
        val channelEvent = getEventChannelForTest<String>()

        channelEvent.postValue("event")

        channelEvent.observe {
            observer.onChanged(it)
        }

        verify(observer).onChanged("event")
    }


    @Test
    /**
     * если событие было прочитано из канала, повторного оповещения не будет
     */
    fun eventChannelTest3() {
        val channelEvent = getEventChannelForTest<String>()

        channelEvent.postValue("event")

        assertEquals("event", channelEvent.getValue())

        channelEvent.observe {
            observer.onChanged(it)
        }

        val mockObserver: Observer<String> = mock()
        channelEvent.observe {
            mockObserver.onChanged(it)
        }

        verify(mockObserver, times(0)).onChanged("event")
    }

    private fun <T> getEventChannelForTest(): DataChannel<T> {
        val owner = mock<LifecycleOwner>()

        val lifecycleRegistry = LifecycleRegistry(owner)
        whenever(owner.lifecycle).thenReturn(lifecycleRegistry)

        val channelFactory = LiveDataChannelFactory()
        val channelEvent = channelFactory.createEventChannel<T>()

        channelFactory.attachOwner(owner)

        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        return channelEvent
    }
}