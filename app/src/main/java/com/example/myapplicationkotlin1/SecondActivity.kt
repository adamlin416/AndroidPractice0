package com.example.myapplicationkotlin1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tv: TextView = TextView(this)
        val string = intent.getStringExtra(Intent.EXTRA_TEXT)
        tv.text = "This is the second activity, the message is: $string"
        setContentView(tv)
    }
}