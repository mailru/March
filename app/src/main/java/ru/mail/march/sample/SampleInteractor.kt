package ru.mail.march.sample

import ru.mail.march.interactor.Interactor

class SampleInteractor : Interactor(), Timer.Observer {
    val errorChannel = channelFactory.createEventChannel<String>()
    val timeChannel = channelFactory.createStateChannel<Int>()
    val wordChannel = channelFactory.createStateChannel<String>()

    override fun create() {
        Timer.registerObserve(this)
    }

    override fun destroy() {
        Timer.unregisterObserver(this)
    }

    fun request() {
        errorChannel.postValue(RandomGenerator.generateError())
    }

    override fun onTimeChanged(time: Int) {
        timeChannel.postValue(time)

        if (time % 5 == 0) {
            wordChannel.postValue(RandomGenerator.generateWord())
        }
    }
}