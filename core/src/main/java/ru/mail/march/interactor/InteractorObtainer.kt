package ru.mail.march.interactor

import ru.mail.march.channel.DataChannelFactory

typealias InteractorCreator<T> = (channelFactory: DataChannelFactory) -> T

interface InteractorObtainer {
    fun <T : Interactor> obtain(clazz: Class<T>, creator: InteractorCreator<T>): T

    fun <T : Interactor> obtain(clazz: Class<T>, key: String, creator: InteractorCreator<T>): T
}

inline fun <reified T : Interactor, I: InteractorObtainer> I.obtain(noinline creator: InteractorCreator<T>): T {
    return this.obtain(T::class.java, creator)
}

inline fun <reified T : Interactor, I: InteractorObtainer> I.obtain(key: String, noinline creator: InteractorCreator<T>): T {
    return this.obtain(T::class.java, key, creator)
}
