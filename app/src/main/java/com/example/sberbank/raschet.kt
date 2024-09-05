package com.example.sberbank

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class raschet : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_raschet)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val bundle = intent.extras
        val monts = bundle!!.getInt("monts")
        val sum = bundle.getDouble("Sum")
        val sumText: TextView = findViewById(R.id.sum)
        val monthsText: TextView = findViewById(R.id.months)
        val payText: TextView = findViewById(R.id.payment)

        sumText.text = sum.toString()
        monthsText.text = monts.toString()
        payText.text = calc(monts,sum).toString()
    }

    fun calc (months:Int,sum:Double):Double{
        return when{
            months <= 12 ->{
                sum / months + (sum* 0.059)
            }
            months <= 24 ->{
                val s1 = sum / 12 + (sum * 0.059)
                ((s1 + sum) / months) + ((sum - s1) * 0.051)
            }
            else ->{
                val s1 = sum / 12 + (sum * 0.059)
                ((s1 + sum) / months) + ((sum - s1) * 0.042)
            }
        }
    }
}