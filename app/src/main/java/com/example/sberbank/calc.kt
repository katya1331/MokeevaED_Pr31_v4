package com.example.sberbank

import android.content.Intent
import android.os.Bundle
import android.provider.Telephony.Mms.Intents
import android.view.View
import android.widget.SeekBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class calc : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calc)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val seekbar: SeekBar = findViewById(R.id.seekBar)
        val months: Int = findViewById<View?>(R.id.months).toString().toInt()
        val btn:AppCompatButton = findViewById(R.id.calculateBtn)

        btn.setOnClickListener{
            val bundle = Bundle().apply {
                putInt("monts",months)
                putDouble("Sum",seekbar.progress.toDouble())
            }
            val intent = Intent(this,raschet::class.java).apply { putExtras(bundle) }
            startActivity(intent)
            finish()
        }
    }
}