package com.ml.status

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.ml.status.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.idBtnStories)
        button.setOnClickListener {
            showStories()
        }
    }
    fun showStories() {
        val i = Intent(this, Stories::class.java)
        startActivity(i)
    }
}