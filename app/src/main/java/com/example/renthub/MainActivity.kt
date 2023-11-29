package com.example.renthub

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Button
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val registrarlink = findViewById<Button>(R.id.inisesionbtn)


        registrarlink.setOnClickListener {
            val intent = Intent(this, RegistrarUsuario::class.java)
            startActivity(intent)
        }

    }
}
