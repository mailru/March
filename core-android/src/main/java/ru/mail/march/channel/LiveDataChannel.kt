package ru.mail.march.channel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

open class LiveDataChannel<T> : DataChannel<T> {
    private val liveData = ExpandedLiveData<T>()
    private var lifecycleOwner: LifecycleOwner? = null

    override fun postValue(value: T) {
        liveData.postValue(value)
    }

    override fun observe(observer: DataChannel.Observer<T>) {
        observe(observer::onChanged)
    }

    override fun observe(observer: (T) -> Unit) {
        lifecycleOwner?.let { owner ->
            liveData.observe(owner,
                ExpandedLifecycleObserver(
                    observer
                )
            )
        }
    }

    fun attachOwner(owner: LifecycleOwner) {
        lifecycleOwner = owner
    }

    inner class ExpandedLiveData<T> : MutableLiveData<T>() {
        override fun onInactive() {
            lifecycleOwner = null
        }
    }

    class ExpandedLifecycleObserver<T>(private val channelObserver: (T) -> Unit) : Observer<T> {
        override fun onChanged(t: T?) {
            if (t != null) {
                channelObserver(t)
            }
        }
    }

    override fun getValue(): T? {
        return liveData.value
    }
}