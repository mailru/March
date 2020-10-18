package ru.mail.march.interactor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ru.mail.march.channel.LiveDataChannelFactory

class InteractorViewModel<I : Interactor>(
    val interactor: I,
    val channelFactory: LiveDataChannelFactory
) : ViewModel() {

    init {
        interactor.create()
        interactor.interactorScope = viewModelScope
    }

    override fun onCleared() {
        interactor.destroy()
    }
}