package com.example.donaparato

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.lang.reflect.Array.newInstance
import java.net.URLClassLoader.newInstance

class MainActivity : AppCompatActivity() {
    private lateinit var etName: EditText
    private lateinit var etLastName: EditText
    private lateinit var etAge: EditText
    private lateinit var etPhone: EditText
    private lateinit var etProduct: EditText
    private lateinit var etUnitPrice: EditText
    private lateinit var etQuantity: EditText
    private lateinit var btnPurchase: Button
    private lateinit var btnUserInfo: Button
    private lateinit var btnClear: Button
    private lateinit var btnhelp: Button
    private lateinit var tipoafiliacion:Spinner


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etName = findViewById(R.id.nombreEditText)
        etLastName = findViewById(R.id.apellidoEditText)
        etAge = findViewById(R.id.edadEditText)
        etPhone = findViewById(R.id.telefonoEditText)
        etProduct = findViewById(R.id.nombreProductoEditText)
        etUnitPrice = findViewById(R.id.valorUnitarioEditText)
        etQuantity = findViewById(R.id.cantidadEditText)

        btnhelp = findViewById(R.id.help_button)
        btnPurchase = findViewById(R.id.realizarCompraButton)
        btnUserInfo = findViewById(R.id.mostrarDatosButton)
        btnClear = findViewById(R.id.limpiarButton)
        tipoafiliacion = findViewById(R.id.tipoUsuarioSpinner)

        btnhelp.setOnClickListener { help() }
        btnPurchase.setOnClickListener { purchase() }
        btnUserInfo.setOnClickListener { showUserInfo() }
        btnClear.setOnClickListener { clear() }

    }


    private fun clear() {
        etName.text.clear()
        tipoafiliacion.setSelection(0)
        etAge.text.clear()
        etLastName.text.clear()
        etPhone.text.clear()
        etProduct.text.clear()
        etUnitPrice.text.clear()
        etQuantity.text.clear()
    }

    private fun help() {
        val intent = Intent(this, ayudas::class.java)
        startActivity(intent)
    }

    private fun purchase() {
        // Validar que se ingresen todos los campos
        if (etName.text.isBlank() || etAge.text.isBlank() || etPhone.text.isBlank()
            || etProduct.text.isBlank() || etUnitPrice.text.isBlank() || etQuantity.text.isBlank()
        ) {
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        // Calcular total a pagar y descuento según tipo de usuario
        val unitPrice = etUnitPrice.text.toString().toDouble()
        val quantity = etQuantity.text.toString().toInt()
        val total = unitPrice * quantity
        var discount = 0.0

        // Obtener tipo de usuario seleccionado
        val spinner = findViewById<Spinner>(R.id.tipoUsuarioSpinner)
        val tipos_usuario = spinner.selectedItem.toString()
        when (tipos_usuario) {
            "Tipo A" -> discount = total * 0.4
            "Tipo B" -> discount = total * 0.2
            "Tipo C" -> discount = total * 0.1
            "Ninguna de las anteriores" -> discount = 0.0
        }

        val finalPrice = total - discount

    // Mostrar resultado
        val message = "Nombre: ${etName.text}\nTipo de usuario: $tipos_usuario\n" +
                "Precio total: \$$total\nDescuento: \$$discount\n" +
                "Precio final: \$$finalPrice"
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()

        if (discount == 0.0) {
            val note = "Nombre: ${etName.text}\nTipo de usuario: $tipos_usuario\n" +
                    "Precio total: \$$total\nDescuento: No se le realizará descuento\n" +
                    "Precio final: \$$finalPrice"
            Toast.makeText(this, note, Toast.LENGTH_LONG).show()

        }
    }
    private fun showUserInfo() {
        val nombre: EditText? = findViewById(R.id.nombreEditText)
        val apellido: EditText? = findViewById(R.id.apellidoEditText)
        val edad: EditText? = findViewById(R.id.edadEditText)
        val tipo_afiliacion = findViewById<Spinner>(R.id.tipoUsuarioSpinner)
        val celular: EditText? = findViewById(R.id.telefonoEditText)

        val name: String = nombre?.text.toString()
        val lastname: String = apellido?.text.toString()
        val age: Int = edad?.text.toString().toIntOrNull() ?: 0
        val tipos_usuario = tipo_afiliacion.selectedItem.toString()
        val phone: String = celular?.text.toString()



        // Validar que se haya ingresado información de usuario
        if (name.isBlank() && age == 0 ) {
            Toast.makeText(this, "No se han ingresado datos de usuario", Toast.LENGTH_SHORT).show()
            return
        }
        val intent = Intent(this, DatosUsuario::class.java)
        val miBundle: Bundle = Bundle()

        miBundle.putString("Nombre", name)
        miBundle.putString("Apellido", lastname)
        miBundle.putInt("Edad", age)
        miBundle.putString("Afiliación", tipos_usuario)
        miBundle.putString("Telefono", phone)

        intent.putExtras(miBundle)
        startActivity(intent)
    }


}





