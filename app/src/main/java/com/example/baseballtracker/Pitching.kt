package com.example.baseballtracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.result.contract.ActivityResultContracts

class Pitching : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pitching)

//        var spinner = findViewById<Spinner>(R.id.spinner)
//        ArrayAdapter.createFromResource(
//            this,
//            R.array.pitch_types,
//            android.R.layout.simple_spinner_item
//        ).also { adapter ->
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//            spinner.adapter = adapter
//        }

//        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onNothingSelected(parent: AdapterView<>?) {
//            }
//
//            override fun onItemSelected(parent: AdapterView<>?, view: View?, position: Int, id: Long) {
//                val spinnerSelected = position
//            }




        // TODO: Get results back
        var ball = findViewById<Button>(R.id.pichBall)
        ball.setOnClickListener{
            val intent = Intent(this,SelectPitch::class.java)
            startActivity(intent)
        }

        var strike = findViewById<Button>(R.id.pitchStrike)
        strike.setOnClickListener{
            val intent = Intent(this,SelectPitch::class.java)
            startActivity(intent)
        }


        var pitcher = findViewById<Spinner>(R.id.pitcher)
        ArrayAdapter.createFromResource(
            this,
            R.array.pitchers,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            pitcher.adapter = adapter
        }

//        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onNothingSelected(parent: AdapterView<>?) {
//            }
//
//            override fun onItemSelected(parent: AdapterView<>?, view: View?, position: Int, id: Long) {
//                val spinnerSelected = position
//            }


    }
}