package com.example.baseballtracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView

const val HOME_PLAYER_STATS = "com.example.baseballtracker.home_player_stats"
const val AWAY_PLAYER_STATS = "com.example.baseballtracker.away_player_stats"
const val HOME_PITCHER_STATS = "com.example.baseballtracker.home_pitcher_stats"
const val AWAY_PITCHER_STATS = "com.example.baseballtracker.away_pitcher_stats"

class GameStatsActivity : AppCompatActivity() {
    lateinit var homeBattingGameStats: HashMap<String, PlayerGameStats>
    lateinit var awayBattingGameStats: HashMap<String, PlayerGameStats>
    lateinit var homePitchingGameStats: HashMap<String, PitcherGameStats>
    lateinit var awayPitchingGameStats: HashMap<String, PitcherGameStats>
    lateinit var homeAwayRadioGroup: RadioGroup
    lateinit var pitchingBattingRadioGroup: RadioGroup
    var homeActive = true
    var pitchingActive = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_stats)

        homeBattingGameStats = intent.getSerializableExtra(HOME_PLAYER_STATS) as HashMap<String, PlayerGameStats>
        awayBattingGameStats = intent.getSerializableExtra(AWAY_PLAYER_STATS) as HashMap<String, PlayerGameStats>
        homePitchingGameStats = intent.getSerializableExtra(HOME_PITCHER_STATS) as HashMap<String, PitcherGameStats>
        awayPitchingGameStats = intent.getSerializableExtra(AWAY_PITCHER_STATS) as HashMap<String, PitcherGameStats>

        Log.d("Test2", homePitchingGameStats.toString())
        Log.d("Test2", awayPitchingGameStats.toString())

        homeAwayRadioGroup = findViewById(R.id.game_stats_home_away_radio_group)
        pitchingBattingRadioGroup = findViewById(R.id.game_stats_pitching_batting_radio_group)

        val homeBattingFragment: Fragment = BattingGameStatsFragment.Companion.newInstance(homeBattingGameStats)
        val awayBattingFragment: Fragment = BattingGameStatsFragment.Companion.newInstance(awayBattingGameStats)
        val homePitchingFragment: Fragment = PitchingGameStatsFragment.Companion.newInstance(homePitchingGameStats)
        val awayPitchingFragment: Fragment = PitchingGameStatsFragment.Companion.newInstance(awayPitchingGameStats)

        supportFragmentManager.beginTransaction().add(R.id.game_stats_fragment_container_view, homeBattingFragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.game_stats_fragment_container_view, awayBattingFragment).detach(awayBattingFragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.game_stats_fragment_container_view, homePitchingFragment).detach(homePitchingFragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.game_stats_fragment_container_view, awayPitchingFragment).detach(awayPitchingFragment).commit()

        // home away radio group
        homeAwayRadioGroup.setOnCheckedChangeListener { radioGroup, i ->
            if (i == R.id.home_radio_button) {
                homeActive = true
                if (pitchingActive) {
                    supportFragmentManager.beginTransaction().detach(awayPitchingFragment).attach(homePitchingFragment).commit()
                } else {
                    supportFragmentManager.beginTransaction().detach(awayBattingFragment).attach(homeBattingFragment).commit()
                }
            } else {
                homeActive = false
                if (pitchingActive) {
                    supportFragmentManager.beginTransaction().detach(homePitchingFragment).attach(awayPitchingFragment).commit()
                } else {
                    supportFragmentManager.beginTransaction().detach(homeBattingFragment).attach(awayBattingFragment).commit()
                }
            }
        }

        // pitching hitting radio group
        pitchingBattingRadioGroup.setOnCheckedChangeListener { radioGroup, i ->
            if (i == R.id.pitching_radio_button) {
                pitchingActive = true
                if (homeActive) {
                    supportFragmentManager.beginTransaction().detach(homeBattingFragment).attach(homePitchingFragment).commit()
                } else {
                    supportFragmentManager.beginTransaction().detach(awayBattingFragment).attach(awayPitchingFragment).commit()
                }
            } else {
                pitchingActive = false
                if (homeActive) {
                    supportFragmentManager.beginTransaction().detach(homePitchingFragment).attach(homeBattingFragment).commit()
                } else {
                    supportFragmentManager.beginTransaction().detach(awayPitchingFragment).attach(awayBattingFragment).commit()
                }
            }
        }
    }
}