package com.example.baseballtracker

import java.util.*

class Lineup : java.io.Serializable {
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

    fun movePlayerUp(playerIndex: Int) {
        if (playerIndex != 0) {
            val playerId = battingOrder[playerIndex]
            val playerBeforeId = battingOrder[playerIndex - 1]
            battingOrder[playerIndex] = playerBeforeId
            battingOrder[playerIndex - 1] = playerId
        }
    }

    fun movePlayerDown(playerIndex: Int) {
        if (playerIndex != battingOrder.size - 1) {
            val playerId = battingOrder[playerIndex]
            val playerAfterId = battingOrder[playerIndex + 1]
            battingOrder[playerIndex] = playerAfterId
            battingOrder[playerIndex + 1] = playerId
        }
    }

    fun getPlayerNameByIndex(playerIndex: Int): String {
        val playerId = battingOrder[playerIndex]
        return playerNames[playerId]!!
    }
}
