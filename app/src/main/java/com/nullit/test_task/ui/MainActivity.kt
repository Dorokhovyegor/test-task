package com.nullit.test_task.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nullit.test_task.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        actionBar?.hide()
    }
}