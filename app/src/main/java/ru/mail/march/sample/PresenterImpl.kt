package ru.mail.march.sample

import ru.mail.march.interactor.InteractorObtainer

class PresenterImpl(view: Presenter.View, interactorObtainer: InteractorObtainer): Presenter {
    private val interactor = interactorObtainer.obtain(SampleInteractor::class.java) {
        SampleInteractor()
    }

    init {
        interactor.errorChannel.observe { view.showError(it) }
        interactor.wordChannel.observe { view.showText(it) }
        interactor.timeChannel.observe { view.showTime(it) }
    }

    override fun onButtonClick() {
        interactor.request()
    }
}