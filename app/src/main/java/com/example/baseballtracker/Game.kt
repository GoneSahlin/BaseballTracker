package com.example.baseballtracker

import java.util.*

class Game(var totalInnings: Int) {
    var homeName: String = "Home"
    var awayName: String = "Away"
    var homePlayers: Vector<Int> = Vector<Int>()
    var awayPlayers: Vector<Int> = Vector<Int>()
    var outs: Int = 0
    var strikes: Int = 0
    var balls: Int = 0
    var homeHitting: Boolean = false
    var inning: Int = 1
    var playerUpHomeIndex: Int = 0
    var playerUpAwayIndex: Int = 0
    var runsHome: Int = 0
    var runsAway: Int = 0
    var baseRunners: BaseRunners = BaseRunners()


    fun runsScored(runnersScored: Set<Int>) {
        for (runner in runnersScored) {
            if (homeHitting) {
                runsHome++
            } else {
                runsAway++
            }
        }
    }

    fun baseHit(bases: Int) {
        val runnersScored = baseRunners.hit(bases)

        runsScored(runnersScored)
        nextBatter()
    }

    fun out(base: Int) {
        outs++
        baseRunners.out(base)
        if (outs >= 3) {
            nextHalfInning()
        } else {
            nextBatter()
        }
    }

    fun nextHalfInning() {

    }

    fun walk() {
        val runnersScored = baseRunners.hit(1)

        runsScored(runnersScored)
        nextBatter()
    }

    fun strike() {
        strikes++
        if (strikes >= 3) {
            strikeOut()
        }
    }

    fun strikeOut() {
        
    }

    fun outAt(base: Int) {

    }

    fun ball() {
        balls++
        if (balls >= 4) {
            walk()
        }
    }

    fun foul() {
        if (strikes == 3) {
            return
        } else {
            strike()
        }
    }

    fun nextBatter() {
        strikes = 0
        balls = 0
        if (homeHitting) {
            playerUpHomeIndex = (playerUpHomeIndex + 1) % homePlayers.size
            baseRunners.setBatter(homePlayers[playerUpHomeIndex])
        } else {
            playerUpAwayIndex = (playerUpAwayIndex + 1) % awayPlayers.size
            baseRunners.setBatter(awayPlayers[playerUpAwayIndex])
        }

    }

    fun getRunner(base: Int): Int? {
        return baseRunners.getRunner(base)
    }

    fun setBaseRunners() {

    }

    fun endGame() {

    }
}