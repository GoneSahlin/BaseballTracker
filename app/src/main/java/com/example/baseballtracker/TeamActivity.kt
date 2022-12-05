package com.example.baseballtracker

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.view.isVisible
import java.util.Vector

class TeamActivity : AppCompatActivity() {
    private lateinit var team: TableManager
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team)
        findViewById<Button>(R.id.make_Team).setOnClickListener{makeTeam()}
        findViewById<Button>(R.id.add_player).setOnClickListener{addplayer()}
        findViewById<Button>(R.id.add_player).isVisible = false
        findViewById<Button>(R.id.remove_player).isVisible = false
        findViewById<Button>(R.id.player_to_add).isVisible = false


    }

    private fun addplayer() {
        findViewById<EditText>(R.id.player_to_add)
        val id = Player().get_id(this)
        var line = Vector<String>()
        val temp = findViewById<EditText>(R.id.player_to_add)
        line.add(temp.toString())
        line.add(id.toString())
        team.table.add(line)
        team.SaveFile()
        temp.setText("")


    }

    @SuppressLint("WrongViewCast")
    private fun makeTeam() {
        val teamname = findViewById<EditText>(R.id.team_name).text.toString()

        team = TableManager(this,teamname)
        findViewById<Button>(R.id.add_player).isVisible = true
        findViewById<Button>(R.id.remove_player).isVisible = true
        findViewById<Button>(R.id.player_to_add).isVisible = true

    }


}