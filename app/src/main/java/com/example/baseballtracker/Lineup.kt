package com.example.baseballtracker

import java.util.*

class Lineup {
    var nextId = 0
    var battingOrder: Vector<Int> = Vector()
    var playerNames: MutableMap<Int, String> = mutableMapOf()
    var pitcher: Int = -1

    fun addPlayer(playerName: String) {
        val playerId = nextId
        nextId++

        battingOrder.add(playerId)
        playerNames[playerId] = playerName
    }

    fun removePlayerByIndex(battingOrderIndex: Int) {
        val playerId = battingOrder[battingOrderIndex]
        battingOrder.remove(battingOrderIndex)
        playerNames.remove(playerId)
    }

    fun removePlayerById(playerId: Int) {
        val battingOrderIndex = battingOrder.indexOf(playerId)
        battingOrder.remove(battingOrderIndex)
        playerNames.remove(playerId)
    }

    fun movePlayerUp(playerId: Int) {
        
    }
}