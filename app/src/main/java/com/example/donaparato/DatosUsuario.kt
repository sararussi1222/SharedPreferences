package com.example.donaparato

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class DatosUsuario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos_usuario)
        val campoMensaje = findViewById<TextView>(R.id.txtMensaje)

        val miBundle: Bundle? = this.intent.extras

        if (miBundle != null) {
            campoMensaje.text =
                    "Nombre: ${miBundle.getString("Nombre")}\n" +
                    "Apellido: ${miBundle.getString("Apellido")}\n" +
                    "Edad: ${miBundle.getInt("Edad")}\n" +
                            "Telefono: ${miBundle.getString("Telefono")}\n" +
                            "Tipo de afiliación: ${miBundle.getString("Afiliación")}\n"
        }

        val botonSalir: Button =findViewById(R.id.btnSalir)
        botonSalir.setOnClickListener { onClick() }
    }

    private fun onClick() {
        finish()
    }
    }
