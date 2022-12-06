package com.example.baseballtracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView

const val HOME_PLAYER_STATS = "com.example.baseballtracker.home_player_stats"
const val AWAY_PLAYER_STATS = "com.example.baseballtracker.away_player_stats"

class GameStatsActivity : AppCompatActivity() {
    lateinit var homeBattingGameStats: HashMap<String, PlayerGameStats>
    lateinit var awayBattingGameStats: HashMap<String, PlayerGameStats>
    lateinit var homeAwayRadioGroup: RadioGroup
    var homeActive = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_stats)

        homeBattingGameStats = intent.getSerializableExtra(HOME_PLAYER_STATS) as HashMap<String, PlayerGameStats>
        awayBattingGameStats = intent.getSerializableExtra(AWAY_PLAYER_STATS) as HashMap<String, PlayerGameStats>

        homeAwayRadioGroup = findViewById(R.id.game_stats_home_away_radio_group)

        val homeBattingFragment: Fragment = BattingGameStatsFragment.Companion.newInstance(homeBattingGameStats)
        val awayBattingFragment: Fragment = BattingGameStatsFragment.Companion.newInstance(awayBattingGameStats)

        supportFragmentManager.beginTransaction().add(R.id.game_stats_fragment_container_view, homeBattingFragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.game_stats_fragment_container_view, awayBattingFragment).detach(awayBattingFragment).commit()

        // home away radio group
        homeAwayRadioGroup.setOnCheckedChangeListener { radioGroup, i ->
            if (i == R.id.home_radio_button) {
                homeActive = true
                supportFragmentManager.beginTransaction().detach(awayBattingFragment).attach(homeBattingFragment).commit()
            } else {
                homeActive = false
                supportFragmentManager.beginTransaction().detach(homeBattingFragment).attach(awayBattingFragment).commit()
            }
        }
    }
}