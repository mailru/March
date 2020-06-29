package ru.mail.march.interactor

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.Rule
import org.junit.Test
import ru.mail.march.TestInteractor

class InteractorTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val eventObserver: Observer<String> = mock()
    private val stateObserver: Observer<Int> = mock()


    @Test
    fun `observe events and states`() {
        val interactor = SampleInteractor()
        interactor.eventDataChannel.observe {
            eventObserver.onChanged(it)
        }

        interactor.stateDataChannel.observe {
            stateObserver.onChanged(it)
        }

        interactor.eventDataChannel.postValue("event")
        verify(eventObserver, times(1)).onChanged("event")

        interactor.stateDataChannel.postValue(1)
        verify(stateObserver, times(1)).onChanged(1)
    }
}

class SampleInteractor : TestInteractor() {
    val eventDataChannel = eventChannel<String>()
    val stateDataChannel = eventChannel<Int>()
}