package com.example.baseballtracker

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainSettingsActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_settings)

        findViewById<Button>(R.id.CreateTeam).setOnClickListener{callCreateTeam()}
    }

    private fun callCreateTeam() {
        val intent = Intent(this,TeamActivity::class.java)
        startActivity(intent)
    }
}