package com.example.baseballtracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner

class Pitching : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pitching)

        var spinner = findViewById<Spinner>(R.id.spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.pitch_types,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

//        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onNothingSelected(parent: AdapterView<>?) {
//            }
//
//            override fun onItemSelected(parent: AdapterView<>?, view: View?, position: Int, id: Long) {
//                val spinnerSelected = position
//            }


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