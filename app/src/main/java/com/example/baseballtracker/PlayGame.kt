package com.example.baseballtracker

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts


class PlayGame : AppCompatActivity() {
    private var pitchTypeIndex: Int = -1
    private lateinit var strikeButton: Button
    private lateinit var ballButton: Button
    private lateinit var inPlayButton: Button
    private lateinit var strikeArray: Array<Int>
    private lateinit var ballArray: Array<Int>
    private lateinit var totalArray: Array<Int>
    private lateinit var pitchTypes: Array<String>
    private lateinit var pitchTextViewArray: Array<TextView>
    private lateinit var strikeTextViewArray: Array<TextView>
    private lateinit var ballTextViewArray: Array<TextView>
    private lateinit var totalTextViewArray: Array<TextView>
    private lateinit var allStrikesTextView: TextView
    private lateinit var allBallsTextView: TextView
    private lateinit var allTotalTextView: TextView
    private lateinit var awayScoreTextView: TextView
    private lateinit var homeScoreTextView: TextView
    private var strike = true
    private var allStrikes: Int = 0
    private var allBalls: Int = 0
    private var allPitches: Int = 0
    private var runsAway: Int = 0
    private var runsHome: Int = 0


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

        // add stat textViews
        val layout = findViewById<GridLayout>(R.id.stats_layout)
        allStrikesTextView = findViewById(R.id.all_strikes)
        allBallsTextView = findViewById(R.id.all_balls)
        allTotalTextView = findViewById(R.id.all_total)
        pitchTextViewArray = Array<TextView>(pitchTypes.size) { TextView(this) }
        strikeTextViewArray = Array<TextView>(pitchTypes.size) { TextView(this) }
        ballTextViewArray = Array<TextView>(pitchTypes.size) { TextView(this) }
        totalTextViewArray = Array<TextView>(pitchTypes.size) { TextView(this) }
        for (i in pitchTypes.indices) {
            createTextView(pitchTextViewArray[i])
            createTextView(strikeTextViewArray[i])
            createTextView(ballTextViewArray[i])
            createTextView(totalTextViewArray[i])
            layout.addView(pitchTextViewArray[i])
            layout.addView(strikeTextViewArray[i])
            layout.addView(ballTextViewArray[i])
            layout.addView(totalTextViewArray[i])
        }

        val runForAwayButton = findViewById<Button>(R.id.run_for_away_button)
        val runForHomeButton = findViewById<Button>(R.id.run_for_home_button)
        runForAwayButton.setOnClickListener { runForAwayClicked() }
        runForHomeButton.setOnClickListener { runForHomeClicked() }

        awayScoreTextView = findViewById(R.id.away_score)
        homeScoreTextView = findViewById(R.id.home_score)

        displayStats()
        displayScore()
    }

    private fun createTextView(textView: TextView) {
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.gravity = Gravity.CLIP_HORIZONTAL
        textView.gravity = Gravity.RIGHT
        textView.layoutParams = params
    }

    private fun onStrikeButtonClicked() {
        strike = true;
        val intent = Intent(this, SelectPitch::class.java)
        selectPitchResultLauncher.launch(intent)
    }

    private fun onBallButtonClicked() {
        strike = false
        val intent = Intent(this, SelectPitch::class.java)
        selectPitchResultLauncher.launch(intent)
    }

    val selectPitchResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            pitchTypeIndex = result.data!!.getIntExtra(PITCH_TYPE, -1)

            if (pitchTypeIndex != -1) {
                if (strike) {
                    strikeArray[pitchTypeIndex]++
                    allStrikes++
                } else {
                    ballArray[pitchTypeIndex]++
                    allBalls++
                }
                totalArray[pitchTypeIndex]++
                allPitches++
            }
        } else {
            pitchTypeIndex = -1
        }
        displayStats()
    }

    private fun displayStats() {

        allStrikesTextView.text = allStrikes.toString()
        allBallsTextView.text = allBalls.toString()
        allTotalTextView.text = allPitches.toString()


        for (i in pitchTypes.indices) {
            pitchTextViewArray[i].text = pitchTypes[i]
            strikeTextViewArray[i].text = strikeArray[i].toString()
            ballTextViewArray[i].text = ballArray[i].toString()
            totalTextViewArray[i].text = totalArray[i].toString()

//            strikeTextViewArray
        }
    }

    private fun displayScore() {
        awayScoreTextView.text = getString(R.string.away_score, runsAway)
        homeScoreTextView.text = getString(R.string.home_score, runsHome)
    }

    private fun runForAwayClicked() {
        runsAway++
        displayScore()
    }

    private fun runForHomeClicked() {
        runsHome++
        displayScore()
    }

    private fun onInPlayButtonClicked() {

    }
}