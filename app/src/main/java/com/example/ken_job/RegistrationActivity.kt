package com.example.ken_job

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class RegistrationActivity : AppCompatActivity() {
    lateinit var EdtName:EditText
    lateinit var EdtEmail:EditText
    private lateinit var EdtPass:EditText
    lateinit var EdtConPaa:EditText
    private lateinit var btn_register:Button
    lateinit var btnlogin:TextView
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        EdtName=findViewById(R.id.fullname_registration)
        EdtEmail=findViewById(R.id.email_registration)
        EdtPass=findViewById(R.id.registration_password)
        EdtConPaa=findViewById(R.id.registration_conf_password)
        btn_register=findViewById(R.id.btn_register)
        btnlogin=findViewById(R.id.btn_log_in)
        auth=Firebase.auth

        btn_register.setOnClickListener {
            RegUser()

        }
        btnlogin.setOnClickListener {
            val intent=Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }
    private fun RegUser() {
        val fname = EdtName.text.toString()
        val email = EdtEmail.text.toString()
        val pass = EdtPass.text.toString()
        val confirmpass = EdtConPaa.text.toString()

        if (email.isBlank() || pass.isBlank() || confirmpass.isBlank()) {
            Toast.makeText(this, "Please Email and password cant be blank", Toast.LENGTH_LONG)
                .show()
            return
        } else if (pass != confirmpass) {
            Toast.makeText(this, "Password do not match", Toast.LENGTH_LONG).show()
            return


        }
        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Signed successfully", Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(this, "Failed to create", Toast.LENGTH_LONG).show()
            }

        }

    }

}

