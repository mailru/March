package ru.mail.march.sample

interface Presenter {
    fun onButtonClick()

    interface View {
        fun showText(text: String)
        fun showTime(time: Int)
        fun showError(error: String)
    }
}