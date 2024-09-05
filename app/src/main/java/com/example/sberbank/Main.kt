package com.example.sberbank

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Main : AppCompatActivity() {
    private lateinit var pref: SharedPreferences
    lateinit var login : String
    lateinit var password : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        pref = getSharedPreferences("chrome", MODE_PRIVATE)
        var firstLaunch = getSharedPreferences("isFirstLauch", MODE_PRIVATE)
        var edit = pref.edit()
        edit.putString("ects","ects2023")
        edit.apply()
        if (firstLaunch.getBoolean("first",true)){
            var edit = pref.edit()
            edit.putString("ects","ects2023")
            edit.apply()
            var edit2 = firstLaunch.edit()
            edit2.putBoolean("first",false)
            edit2.apply()
        }

        login = findViewById<EditText>(R.id.editTextLogin).toString()
        password = findViewById<EditText>(R.id.editTextTextPassword).toString()


        var btn = findViewById<AppCompatButton>(R.id.signin)
        btn.setOnClickListener{
            if (login.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Введите логин и пароль", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (pref.getString(login, "") != password){
                Toast.makeText(this, "Неверный пароль ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            startActivity(Intent(this, calc::class.java))
            finish()
        }
    }
}