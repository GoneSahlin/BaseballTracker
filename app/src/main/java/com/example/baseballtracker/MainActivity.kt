package com.example.baseballtracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       val  practice = findViewById<Button>(R.id.practice)
        practice.setOnClickListener{
            val intent = Intent(this,PracticeMain::class.java)
            startActivity(intent)
        }
        val  play = findViewById<Button>(R.id.playGame)
        play.setOnClickListener{
            val intent = Intent(this,playgame::class.java)
            startActivity(intent)
        }
        val  stats = findViewById<Button>(R.id.stats)
        stats.setOnClickListener{
            val intent = Intent(this,Stats::class.java)
            startActivity(intent)
        }
        val  settings = findViewById<Button>(R.id.settings)
        settings.setOnClickListener{
            val intent = Intent(this,MainSettings::class.java)
            startActivity(intent)
        }

    }
}