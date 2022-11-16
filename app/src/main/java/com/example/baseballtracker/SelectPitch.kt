package com.example.baseballtracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


const val PITCH_TYPE = "com.example.baseballtracker.pitch_type"

class SelectPitch : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_pitch)
    }
}