package com.sema.my2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        val calc = findViewById<TextView>(R.id.calc1)
        calc.text = "test"
    }
}