package ru.mail.march.interactor.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.mail.march.channel.DataChannelFactory
import ru.mail.march.channel.impl.LiveDataChannelFactory
import ru.mail.march.interactor.Interactor

class InteractorViewModelFactory<I : Interactor>(
    private val creator: (DataChannelFactory) -> I
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val channelFactory = LiveDataChannelFactory()
        val interactor = creator(channelFactory)
        return InteractorViewModel(interactor, channelFactory) as T
    }
}