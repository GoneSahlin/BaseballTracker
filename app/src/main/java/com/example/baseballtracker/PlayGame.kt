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
    private var game = Game(9)
    private lateinit var awayScoreTextView: TextView
    private lateinit var homeScoreTextView: TextView
    private lateinit var inningTextView: TextView
    private lateinit var outsTextView: TextView
    private lateinit var countTextView: TextView
    private lateinit var pitcherTextView: TextView
    private lateinit var batterTextView: TextView
    private lateinit var strikeButton: Button
    private lateinit var ballButton: Button
    private lateinit var inPlayButton: Button
    private lateinit var runScoredButton: Button
    private lateinit var statsButton: Button
    private lateinit var lineupButton: Button
    private var strike = true
//    private lateinit var strikeArray: Array<Int>
//    private lateinit var ballArray: Array<Int>
//    private lateinit var totalArray: Array<Int>
//    private lateinit var pitchTypes: Array<String>
//    private lateinit var pitchTextViewArray: Array<TextView>
//    private lateinit var strikeTextViewArray: Array<TextView>
//    private lateinit var ballTextViewArray: Array<TextView>
//    private lateinit var totalTextViewArray: Array<TextView>
//    private lateinit var allStrikesTextView: TextView
//    private lateinit var allBallsTextView: TextView
//    private lateinit var allTotalTextView: TextView
//    private var allStrikes: Int = 0
//    private var allBalls: Int = 0
//    private var allPitches: Int = 0
//    private var runsAway: Int = 0
//    private var runsHome: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playgame)

        // find views
        awayScoreTextView = findViewById(R.id.away_score)
        homeScoreTextView = findViewById(R.id.home_score)
        inningTextView = findViewById(R.id.inning)
        outsTextView = findViewById(R.id.outs)
        countTextView = findViewById(R.id.count)
        pitcherTextView = findViewById(R.id.pitcher)
        batterTextView = findViewById(R.id.batter)

        strikeButton = findViewById(R.id.strike_button)
        ballButton = findViewById(R.id.ball_button)
        inPlayButton = findViewById(R.id.in_play_button)
        runScoredButton = findViewById(R.id.run_button)
        statsButton = findViewById(R.id.stats_button)
        lineupButton = findViewById(R.id.lineup_button)

        strikeButton.setOnClickListener { onStrikeButtonClicked() }
        ballButton.setOnClickListener { onBallButtonClicked() }
        inPlayButton.setOnClickListener { onInPlayButtonClicked() }
        runScoredButton.setOnClickListener { onRunScoredButtonClicked() }
        statsButton.setOnClickListener { onStatsButtonClicked() }
        lineupButton.setOnClickListener { onLineupButtonClicked() }


//        // initialize arrays
//        pitchTypes = resources.getStringArray(R.array.pitch_types)
//        strikeArray = Array<Int>(pitchTypes.size) {0}
//        ballArray = Array<Int>(pitchTypes.size) {0}
//        totalArray = Array<Int>(pitchTypes.size) {0}

//        // add stat textViews
//        val layout = findViewById<GridLayout>(R.id.stats_layout)
//        allStrikesTextView = findViewById(R.id.all_strikes)
//        allBallsTextView = findViewById(R.id.all_balls)
//        allTotalTextView = findViewById(R.id.all_total)
//        pitchTextViewArray = Array<TextView>(pitchTypes.size) { TextView(this) }
//        strikeTextViewArray = Array<TextView>(pitchTypes.size) { TextView(this) }
//        ballTextViewArray = Array<TextView>(pitchTypes.size) { TextView(this) }
//        totalTextViewArray = Array<TextView>(pitchTypes.size) { TextView(this) }
//        for (i in pitchTypes.indices) {
//            createTextView(pitchTextViewArray[i])
//            createTextView(strikeTextViewArray[i])
//            createTextView(ballTextViewArray[i])
//            createTextView(totalTextViewArray[i])
//            layout.addView(pitchTextViewArray[i])
//            layout.addView(strikeTextViewArray[i])
//            layout.addView(ballTextViewArray[i])
//            layout.addView(totalTextViewArray[i])
//        }

//        val runForAwayButton = findViewById<Button>(R.id.run_for_away_button)
//        val runForHomeButton = findViewById<Button>(R.id.run_for_home_button)
//        runForAwayButton.setOnClickListener { runForAwayClicked() }
//        runForHomeButton.setOnClickListener { runForHomeClicked() }



//        displayStats()
        updateScoreboard()
    }

//    private fun createTextView(textView: TextView) {
//        val params = LinearLayout.LayoutParams(
//            LinearLayout.LayoutParams.MATCH_PARENT,
//            LinearLayout.LayoutParams.WRAP_CONTENT
//        )
//        params.gravity = Gravity.CLIP_HORIZONTAL
//        textView.gravity = Gravity.RIGHT
//        textView.layoutParams = params
//    }

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
            val pitchTypeIndex = result.data!!.getIntExtra(PITCH_TYPE, -1)

            if (pitchTypeIndex != -1) {
                if (strike) {
                    game.strike()
//                    strikeArray[pitchTypeIndex]++
//                    allStrikes++
                } else {
                    game.ball()
//                    ballArray[pitchTypeIndex]++
//                    allBalls++
                }
//                totalArray[pitchTypeIndex]++
//                allPitches++
            }
        } else {
//            pitchTypeIndex = -1
        }
        updateScoreboard()
    }

    private fun onInPlayButtonClicked() {
        val intent = Intent(this, InPlayActivity::class.java)
        inPlayResultLauncher.launch(intent)
    }

    val inPlayResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val outcomeIndex = result.data!!.getIntExtra(IN_PLAY_OUTCOME, -1)
            val outcomes = InPlayOutcome.values()

            when (outcomes[outcomeIndex]) {
                InPlayOutcome.FOUL -> {
                    game.foul()
                }
                InPlayOutcome.OUT -> {
                    game.out()
                }
                InPlayOutcome.SINGLE -> {
                    game.baseHit(1)
                }
                InPlayOutcome.DOUBLE -> {
                    game.baseHit(2)
                }
                InPlayOutcome.TRIPLE -> {
                    game.baseHit(3)
                }
                InPlayOutcome.HOME_RUN -> {
                    game.baseHit(4)
                }
            }
        }
        updateScoreboard()
    }

    private fun onRunScoredButtonClicked() {
        game.runScored()

        updateScoreboard()
    }

    private fun onStatsButtonClicked() {
        TODO("Not yet implemented")
    }

    private fun onLineupButtonClicked() {
        val intent = Intent(this,LineupActivity::class.java)
        startActivity(intent)

        updateScoreboard()
    }


//    private fun displayStats() {
//
//        allStrikesTextView.text = allStrikes.toString()
//        allBallsTextView.text = allBalls.toString()
//        allTotalTextView.text = allPitches.toString()
//
//
//        for (i in pitchTypes.indices) {
//            pitchTextViewArray[i].text = pitchTypes[i]
//            strikeTextViewArray[i].text = strikeArray[i].toString()
//            ballTextViewArray[i].text = ballArray[i].toString()
//            totalTextViewArray[i].text = totalArray[i].toString()
//
////            strikeTextViewArray
//        }
//    }

    private fun updateScoreboard() {
        awayScoreTextView.text = getString(R.string.away_score, game.runsAway)
        homeScoreTextView.text = getString(R.string.home_score, game.runsHome)
        if (game.homeHitting) {
            inningTextView.text = getString(R.string.inning_bottom, game.inning)
        } else {
            inningTextView.text = getString(R.string.inning_top, game.inning)
        }
        outsTextView.text = getString(R.string.outs, game.outs)
        countTextView.text = getString(R.string.count, game.balls, game.strikes)
    }

//    private fun runForAwayClicked() {
//        runsAway++
//        displayScore()
//    }
//
//    private fun runForHomeClicked() {
//        runsHome++
//        displayScore()
//    }


}