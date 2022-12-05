package com.example.baseballtracker

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

const val HOME_LINEUP = "com.example.baseballtracker.home_lineup"
const val AWAY_LINEUP = "com.example.baseballtracker.away_lineup"

class LineupActivity : AppCompatActivity() {
    lateinit var homeLineup: Lineup
    lateinit var awayLineup: Lineup
    lateinit var saveButton: Button
    lateinit var homeAwayRadioGroup: RadioGroup
    lateinit var enterPlayerEditText: EditText
    lateinit var addButton: Button
    lateinit var pitcherSpinner: Spinner
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lineup)

        homeLineup = intent.getSerializableExtra(HOME_LINEUP) as Lineup
        awayLineup = intent.getSerializableExtra(AWAY_LINEUP) as Lineup

        saveButton = findViewById(R.id.save_button)
        homeAwayRadioGroup = findViewById(R.id.home_away_radio_group)
        enterPlayerEditText = findViewById(R.id.enter_player)
        addButton = findViewById(R.id.add_button)
        pitcherSpinner = findViewById(R.id.pitcher_spinner)

        saveButton.setOnClickListener { onSaveButtonClicked() }
        addButton.setOnClickListener { onAddButtonClicked() }

        recyclerView = findViewById(R.id.lineup_recyler_view)
        recyclerView.adapter = ItemAdapter(this, homeLineup)

    }

    private fun getActiveLineup() : Lineup {
        return homeLineup
    }

    private fun onAddButtonClicked() {
        // Ignore any leading or trailing spaces
        val playerName = enterPlayerEditText.text.toString().trim()

        // Clear the EditText so it's ready for another item
        enterPlayerEditText.setText("")

        // Add the item to the list
        if (playerName.isNotEmpty()) {
            val lineup = getActiveLineup()
            lineup.addPlayer(playerName)
        }

        recyclerView.adapter!!.notifyDataSetChanged()
    }

    private fun onSaveButtonClicked() {
        intent.putExtra(HOME_LINEUP, homeLineup)
        intent.putExtra(AWAY_LINEUP, awayLineup)
        setResult(RESULT_OK, intent)
        finish()
    }

    private class ItemAdapter(
        private val context: Context,
        private val lineup: Lineup
    ) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
        class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
            val textView: TextView = view.findViewById(R.id.player_name)
            val upButton: Button = view.findViewById(R.id.move_up_button)
            val downButton: Button = view.findViewById(R.id.move_down_button)
            val removeButton: Button = view.findViewById(R.id.remove_button)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            val adapterLayout = LayoutInflater.from(parent.context)
                .inflate(R.layout.lineup_list_item, parent, false)

            return ItemViewHolder(adapterLayout)
        }

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            val playerName = lineup.getPlayerNameByIndex(position)
            holder.textView.text = playerName

            holder.upButton.setOnClickListener { onMoveUpClicked(position) }
            holder.downButton.setOnClickListener { onMoveDownClicked(position) }
            holder.removeButton.setOnClickListener { onRemoveClicked(position) }
        }

        override fun getItemCount(): Int {
            return lineup.battingOrder.size
        }

        fun onMoveUpClicked(position: Int) {

            lineup.movePlayerUp(position)
            this.notifyDataSetChanged()
        }

        fun onMoveDownClicked(position: Int) {
            lineup.movePlayerDown(position)
            this.notifyDataSetChanged()
        }

        fun onRemoveClicked(position: Int) {
            lineup.removePlayerByIndex(position)
            this.notifyItemRemoved(position)
            this.notifyItemRangeChanged(position, lineup.battingOrder.size)
        }


    }

}