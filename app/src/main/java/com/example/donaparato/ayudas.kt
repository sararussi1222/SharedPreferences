package com.example.donaparato

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ayudas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ayudas)
        val exitActivityButton: Button = findViewById(R.id.exit_button)
        exitActivityButton.setOnClickListener {
            onClick()

        }
    }

    private fun onClick() {
        finish()
    }
}