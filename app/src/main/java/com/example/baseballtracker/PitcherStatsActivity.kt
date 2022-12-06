package com.example.baseballtracker

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

const val PITCH_TYPE_STRIKES = "com.example.baseballtracker.pitch_type_strikes"
const val PITCH_TYPE_BALLS = "com.example.baseballtracker.pitch_type_balls"



class PitcherStatsActivity : AppCompatActivity() {
    lateinit var pitchTypeStrikes: Map<String, Int>
    lateinit var pitchTypeBalls: Map<String, Int>
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pitcher_stats)

        pitchTypeStrikes = intent.getSerializableExtra(PITCH_TYPE_STRIKES) as Map<String, Int>
        pitchTypeBalls = intent.getSerializableExtra(PITCH_TYPE_BALLS) as Map<String, Int>

        recyclerView = findViewById(R.id.pitcher_recycler_view)

        recyclerView.adapter = ItemAdapter(
            this,
            pitchTypeStrikes as HashMap<String, Int>,
            pitchTypeBalls as HashMap<String, Int>
        )
    }

    private class ItemAdapter(
        private val context: Context,
        private val pitchTypeStrikes: HashMap<String, Int>,
        private val pitchTypeBalls: HashMap<String, Int>
    ) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
        class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
            val pitchTypeName: TextView = view.findViewById(R.id.pitch_type_name)
            val pitchTypeBalls: TextView = view.findViewById(R.id.pitch_type_balls)
            val pitchTypeStrikes: TextView = view.findViewById(R.id.pitch_type_strikes)
            val pitchTypeTotal: TextView = view.findViewById(R.id.pitch_type_total)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            val adapterLayout = LayoutInflater.from(parent.context)
                .inflate(R.layout.pitch_type_list_item, parent, false)

            return ItemViewHolder(adapterLayout)
        }

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            val pitchTypeNames = pitchTypeBalls.keys.toList()
            val pitchTypeName = pitchTypeNames[position]
            holder.pitchTypeName.text = pitchTypeName
            holder.pitchTypeBalls.text = pitchTypeBalls[pitchTypeName].toString()
            holder.pitchTypeStrikes.text = pitchTypeStrikes[pitchTypeName].toString()
            holder.pitchTypeTotal.text = (pitchTypeBalls[pitchTypeName]!! + pitchTypeStrikes[pitchTypeName]!!).toString()

        }

        override fun getItemCount(): Int {
            return pitchTypeBalls.keys.size
        }

    }
}