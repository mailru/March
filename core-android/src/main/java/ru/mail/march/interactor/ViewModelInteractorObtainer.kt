package ru.mail.march.interactor

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import ru.mail.march.channel.DataChannelFactory

class ViewModelInteractorObtainer(
    private val viewModelStore: ViewModelStore,
    private val lifecycleOwner: LifecycleOwner
) : InteractorObtainer {

    override fun <T : Interactor> obtain(clazz: Class<T>, creator: () -> T): T {
        return obtain(clazz,
            DEFAULT_KEY, creator)
    }

    override fun <T : Interactor> obtain(
        clazz: Class<T>,
        key: String,
        creator: () -> T
    ): T {
        val provider = ViewModelProvider(viewModelStore,
            InteractorViewModelFactory(creator)
        )
        val viewModel = provider.get(prepareKey(clazz, key), InteractorViewModel::class.java)
        val castedViewModel = viewModel as InteractorViewModel<T>

        castedViewModel.channelFactory.attachOwner(lifecycleOwner)

        return castedViewModel.interactor
    }

    private fun prepareKey(clazz: Class<*>, key: String): String {
        val className = clazz.canonicalName
            ?: throw IllegalArgumentException("Local and anonymous classes can not be Interactor")

        return "$className : $key"
    }

    companion object {
        const val DEFAULT_KEY = "Default"
    }
}