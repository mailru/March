package ru.mail.march.sample

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ru.mail.march.interactor.impl.InteractorObtainers

class MainActivity : AppCompatActivity(), Presenter.View {
    private var timeTextView: TextView? = null
    private var wordTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timeTextView = findViewById(R.id.text_time)
        wordTextView = findViewById(R.id.text_word)
        val button = findViewById<Button>(R.id.button_request)

        val presenter = PresenterImpl(this, InteractorObtainers.from(this))
        button.setOnClickListener {
            presenter.onButtonClick()
        }
    }

    override fun showText(text: String) {
        wordTextView?.text = text
    }

    override fun showTime(time: Int) {
        timeTextView?.text = time.toString()
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    data class Proccesor(val x: Int)
}