package com.example.baseballtracker

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.HashMap

private const val PITCHER_GAME_STATS = "pitcher_game_stats"

class PitchingGameStatsFragment : Fragment() {
    private var pitcherGameStats: HashMap<String, PitcherGameStats>? = null
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pitcherGameStats = it.getSerializable(PITCHER_GAME_STATS) as HashMap<String, PitcherGameStats>
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_pitching_game_stats, container, false)

        recyclerView = view.findViewById(R.id.pitching_game_stats_recycler_view)
//        recyclerView.adapter = ItemAdapter(this, activeLineup)
//        Log.d("Test", playerGameStats.toString())
//        recyclerView.adapter = ItemAdapter(this.requireContext(), playerGameStats as HashMap<String, PlayerGameStats>)

        if (pitcherGameStats != null) {
            recyclerView.adapter = ItemAdapter(this.requireContext(), pitcherGameStats as HashMap<String, PitcherGameStats>)
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(pitcherGameStats: HashMap<String,PitcherGameStats>) =
            PitchingGameStatsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(PITCHER_GAME_STATS, pitcherGameStats)
                }
            }
    }

    private class ItemAdapter(
        private val context: Context,
        private val pitcherGameStats: HashMap<String, PitcherGameStats>
    ) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
        class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
            val playerName: TextView = view.findViewById(R.id.player_name)
//            val avg: TextView = view.findViewById(R.id.avg)
//            val obp: TextView = view.findViewById(R.id.obp)
//            val abs: TextView = view.findViewById(R.id.abs)
//            val ops: TextView = view.findViewById(R.id.ops)
//            val hrs: TextView = view.findViewById(R.id.hrs)
//            val ks: TextView = view.findViewById(R.id.ks)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            val adapterLayout = LayoutInflater.from(parent.context)
                .inflate(R.layout.pitching_stats_list_item, parent, false)

            return ItemViewHolder(adapterLayout)
        }

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            val playerNameString = pitcherGameStats.keys.toList()[position]
            holder.playerName.text = playerNameString
//            holder.avg.text = playerGameStats[playerNameString]!!.getAvg()
//            holder.obp.text = playerGameStats[playerNameString]!!.getObp()
//            holder.abs.text = playerGameStats[playerNameString]!!.getAb()
//            holder.ops.text = playerGameStats[playerNameString]!!.getOps()
//            holder.hrs.text = playerGameStats[playerNameString]!!.getHr()
//            holder.ks.text = playerGameStats[playerNameString]!!.getK()
        }

        override fun getItemCount(): Int {
            return pitcherGameStats.size
        }

    }
}