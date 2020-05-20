package ru.mail.march.interactor.impl

import androidx.lifecycle.ViewModel
import ru.mail.march.channel.impl.LiveDataChannelFactory
import ru.mail.march.interactor.Interactor

class InteractorViewModel<I : Interactor>(
    val interactor: I,
    val channelFactory: LiveDataChannelFactory
) : ViewModel() {
    init {
        interactor.create()
    }

    override fun onCleared() {
        interactor.destroy()
    }
}