package ru.mail.march.interactor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.mail.march.channel.DataChannelFactory
import ru.mail.march.channel.LiveDataChannelFactory

class InteractorViewModelFactory<I : Interactor>(
    private val creator: () -> I
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val channelFactory = LiveDataChannelFactory()
        val interactor = creator()
        interactor.attachChannelFactory(channelFactory)
        @Suppress("UNCHECKED_CAST")
        return InteractorViewModel(interactor, channelFactory) as T
    }
}