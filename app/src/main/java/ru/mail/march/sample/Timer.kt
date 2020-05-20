package ru.mail.march.sample

object Timer {
    private val observers = ArrayList<Observer>()

    init {
        Thread {
            var time = 0

            while (true) {
                observers.forEach {
                    it.onTimeChanged(time)
                }
                time++
                Thread.sleep(1000)
            }
        }.start()
    }

    fun registerObserve(observer: Observer) {
        observers.add(observer)
    }

    fun unregisterObserver(observer: Observer) {
        observers.remove(observer)
    }

    interface Observer {
        fun onTimeChanged(time: Int)
    }
}