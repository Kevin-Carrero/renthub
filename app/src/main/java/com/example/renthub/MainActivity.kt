package com.example.renthub

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var registrate: TextView
    lateinit var correo: TextInputEditText
    lateinit var contraseña: TextInputEditText
    lateinit var iniciobtn: Button
    val TAG: String = "MainActivity"
    private lateinit var auth: FirebaseAuth;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        correo = findViewById(R.id.correo)
        contraseña = findViewById(R.id.contraseña)
        iniciobtn = findViewById(R.id.iniciobtn)
        registrate = findViewById(R.id.registrate)
        auth = Firebase.auth

        registrate.setOnClickListener {
            registrar()
        }
        iniciobtn.setOnClickListener{
            iniciobtn.setOnClickListener {
                signIn(correo.text.toString(), contraseña.text.toString())
            }
        }

    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        //updateUI(currentUser)
    }

    fun signIn(usuario: String, clave: String){
        auth.signInWithEmailAndPassword(usuario, clave)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    Toast.makeText(
                        baseContext,
                        "Authentication exitosa.",
                        Toast.LENGTH_SHORT,
                    ).show()

                    irInicio()
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                    updateUI(null)
                }
            }
    }

    fun registrar (){
        val intent = Intent(this,RegistrarUsuario::class.java)
        startActivity(intent)
    }

    fun irInicio (){
        val intent = Intent(this,Vista::class.java)
        startActivity(intent)
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            // El usuario se ha autenticado correctamente, aquí puedes manejar la lógica para redirigir a otra actividad o realizar otras acciones.
            // Por ejemplo:
            Toast.makeText(this, "Autenticación exitosa", Toast.LENGTH_SHORT).show()
            // Aquí puedes redirigir a otra actividad si es necesario
        } else {
            // El usuario no se autenticó correctamente, puedes manejar esto mostrando un mensaje o realizando otra lógica necesaria.
            Toast.makeText(this, "Autenticación fallida", Toast.LENGTH_SHORT).show()
        }
    }
}