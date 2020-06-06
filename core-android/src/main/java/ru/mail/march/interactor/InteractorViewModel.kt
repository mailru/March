package ru.mail.march.interactor

import androidx.lifecycle.ViewModel
import ru.mail.march.channel.LiveDataChannelFactory

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