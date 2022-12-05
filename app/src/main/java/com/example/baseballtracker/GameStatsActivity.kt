package com.example.baseballtracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView

const val HOME_PLAYER_STATS = "com.example.baseballtracker.home_player_stats"
const val AWAY_PLAYER_STATS = "com.example.baseballtracker.away_player_stats"

class GameStatsActivity : AppCompatActivity() {
    lateinit var homeBattingGameStats: HashMap<String, PlayerGameStats>
    lateinit var awayBattingGameStats: HashMap<String, PlayerGameStats>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_stats)

        homeBattingGameStats = intent.getSerializableExtra(HOME_PLAYER_STATS) as HashMap<String, PlayerGameStats>
        awayBattingGameStats = intent.getSerializableExtra(AWAY_PLAYER_STATS) as HashMap<String, PlayerGameStats>

        val fragmentView = findViewById<FragmentContainerView>(R.id.game_stats_fragment_container_view)
        val homeBattingFragment: Fragment = BattingGameStatsFragment.Companion.newInstance(homeBattingGameStats)
        val awayBattingFragment: Fragment = BattingGameStatsFragment.Companion.newInstance(awayBattingGameStats)

        supportFragmentManager.beginTransaction().add(R.id.game_stats_fragment_container_view, homeBattingFragment).commit()

    }
}