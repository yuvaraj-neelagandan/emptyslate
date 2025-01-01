package com.dm.emptyslate

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private lateinit var tvWelcomeText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        init()
    }

    private fun init() {
        tvWelcomeText = findViewById(R.id.tv_splash_text)
        val textToDisplay = resources.getString(R.string.welcome_text)
        slideWelcomeText(textToDisplay, resources.getString(R.string.text_slide_delay).toLong())
    }

    private fun slideWelcomeText(text: String, delayMillis: Long) {
        tvWelcomeText.text = ""
        CoroutineScope(Dispatchers.Main).launch {
            for (i in text.indices) {
                tvWelcomeText.text = text.substring(0, i + 1)
                delay(delayMillis)
            }
            navigateToLogin()
        }
    }

    private fun navigateToLogin(){
        CoroutineScope(Dispatchers.Main).launch {
            delay(resources.getString(R.string.navigation_delay).toLong()) // Wait for the specified delay
            val intent = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
