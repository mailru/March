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
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import ru.mail.march.channel.DataChannel
import ru.mail.march.channel.impl.LiveDataChannelFactory

class StateChannelTests {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val observer: Observer<String> = mock()

    @Test
    fun `notify observer only with unique values`() {
        val channelState = getStateChannelForTest<String>()

        channelState.observe {
            observer.onChanged(it)
        }

        channelState.postValue("state")

        verify(observer, times(1)).onChanged("state")

        channelState.postValue("state")

        verify(observer, times(1)).onChanged("state")
    }

    @Test
    fun `observer will be notified with last state in channel`() {
        val channelState = getStateChannelForTest<String>()

        channelState.postValue("state")

        channelState.observe {
            observer.onChanged(it)
        }

        verify(observer).onChanged("state")
    }


    @Test
    fun `observer will be notified, if state already observed`() {
        val channelState = getStateChannelForTest<String>()

        channelState.postValue("state")

        channelState.observe {
            observer.onChanged(it)
        }

        val mockObserver: Observer<String> = mock()
        channelState.observe {
            mockObserver.onChanged(it)
        }

        verify(mockObserver, times(1)).onChanged("state")
    }

    private fun <T> getStateChannelForTest(): DataChannel<T> {
        val owner = mock<LifecycleOwner>()

        val lifecycleRegistry = LifecycleRegistry(owner)
        whenever(owner.lifecycle).thenReturn(lifecycleRegistry)

        val channelFactory = LiveDataChannelFactory()
        val channelState = channelFactory.createStateChannel<T>()

        channelFactory.attachOwner(owner)

        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        return channelState
    }
}