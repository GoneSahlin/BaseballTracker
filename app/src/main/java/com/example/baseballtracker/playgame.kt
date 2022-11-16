package com.example.baseballtracker

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat


class playgame : AppCompatActivity() {
    private var pitchTypeIndex: Int = -1
    private lateinit var strikeButton: Button
    private lateinit var ballButton: Button
    private lateinit var inPlayButton: Button
    private lateinit var strikeArray: Array<Int>
    private lateinit var ballArray: Array<Int>
    private lateinit var totalArray: Array<Int>
    private lateinit var pitchTypes: Array<String>
    var allStrikes: Int = 0
    var allBalls: Int = 0
    var allPitches: Int = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playgame)

        strikeButton = findViewById(R.id.strike_button)
        ballButton = findViewById(R.id.ball_button)
        inPlayButton = findViewById(R.id.in_play_button)

        strikeButton.setOnClickListener { onStrikeButtonClicked() }
        ballButton.setOnClickListener { onBallButtonClicked() }
        inPlayButton.setOnClickListener { onInPlayButtonClicked() }

        // initialize arrays
        pitchTypes = resources.getStringArray(R.array.pitch_types)
        strikeArray = Array<Int>(pitchTypes.size) {0}
        ballArray = Array<Int>(pitchTypes.size) {0}
        totalArray = Array<Int>(pitchTypes.size) {0}

    }

    private fun onStrikeButtonClicked() {
        startSelectPitch()

        if (pitchTypeIndex != -1) {
            strikeArray[pitchTypeIndex]++
            totalArray[pitchTypeIndex]++
            allStrikes++
            allPitches++
        }
    }

    private fun onBallButtonClicked() {
        startSelectPitch()

        if (pitchTypeIndex != -1) {
            ballArray[pitchTypeIndex]++
            totalArray[pitchTypeIndex]++
            allBalls++
            allPitches++
        }
    }

    private fun onInPlayButtonClicked() {

    }

    private fun startSelectPitch() {
        val intent = Intent(this, SelectPitch::class.java)
        selectPitchResultLauncher.launch(intent)
    }

    val selectPitchResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // Create the "on" button color based on the chosen color ID from ColorActivity
            pitchTypeIndex = result.data!!.getIntExtra(PITCH_TYPE, 0)
//            pitchType = resources.getStringArray(R.array.pitch_types)[pitchTypeIndex]
        } else {
            pitchTypeIndex = -1
        }
    }
}