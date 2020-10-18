package ru.mail.march

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import ru.mail.march.interactor.Interactor

open class TestInteractor : Interactor() {

    init {
        val owner = mock<LifecycleOwner>()
        val lifecycleRegistry = LifecycleRegistry(owner)
        whenever(owner.lifecycle).thenReturn(lifecycleRegistry)
        attachChannelFactory(TestDataChannelFactory(owner))
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
    }
}