package com.example.renthub

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegistrarUsuario  : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth;

    lateinit var correo: TextInputEditText
    lateinit var contraseña: TextInputEditText
    lateinit var registrar: Button
    val TAG: String = "RegistrarUsuario"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_usuario)
        correo = findViewById(R.id.correo)
        contraseña = findViewById(R.id.contraseña)
        registrar = findViewById(R.id.registrarbtn)
        // Initialize Firebase Auth
        auth = Firebase.auth
        registrar.setOnClickListener {
            createAccount(correo.text.toString(),contraseña.text.toString())
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            //reload()
        }
    }

    private fun createAccount(email: String, password: String) {
        Log.d(TAG, "Email: $email, Password: $password")
        // [START create_user_with_email]
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                    updateUI(null)
                }
            }
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