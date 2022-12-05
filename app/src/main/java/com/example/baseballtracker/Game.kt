package com.example.baseballtracker

import java.util.*

class Game(var totalInnings: Int) {
    var homeName: String = "Home"
    var awayName: String = "Away"
    var homeLineup = Lineup()
    var awayLineup = Lineup()
    var outs: Int = 0
    var strikes: Int = 0
    var balls: Int = 0
    var homeHitting: Boolean = false
    var inning: Int = 1
    var playerUpHomeIndex: Int = 0
    var playerUpAwayIndex: Int = 0
    var runsHome: Int = 0
    var runsAway: Int = 0
    var homePlayerStats: MutableMap<String, PlayerGameStats> = mutableMapOf()
    var awayPlayerStats: MutableMap<String, PlayerGameStats> = mutableMapOf()
//    var baseRunners: BaseRunners = BaseRunners()


    fun runScored() {
//        for (runner in runnersScored) {
//            if (homeHitting) {
//                runsHome++
//            } else {
//                runsAway++
//            }
//        }
        if (homeHitting) {
            runsHome++
        } else {
            runsAway++
        }

        // update stats
        getBatterStats().rbis++
    }

    fun baseHit(bases: Int) {
//        val runnersScored = baseRunners.hit(bases)

//        runsScored(runnersScored)

        when (bases) {
            1 -> getBatterStats().singles++
            2 -> getBatterStats().doubles++
            3 -> getBatterStats().triples++
            4 -> getBatterStats().homeRuns++
        }
        getBatterStats().hits++

        nextBatter()
    }

    fun out() {
        outs++
//        baseRunners.out(base)
        if (outs >= 3) {
            nextHalfInning()
        }
        nextBatter()
    }

    fun nextHalfInning() {
        if (homeHitting) {
            inning++
        }
        homeHitting = !homeHitting

        if (inning > totalInnings) {
            endGame()
        }
        outs = 0
        strikes = 0
        balls = 0
    }

    fun walk() {
        getBatterStats().walks++
//        val runnersScored = baseRunners.hit(1)

//        runsScored(runnersScored)
        nextBatter()


    }

    fun strike() {
        strikes++
        if (strikes >= 3) {
            strikeOut()
        }
    }

    fun strikeOut() {
        getBatterStats().strikeOuts++

        out()
    }

    fun ball() {
        balls++
        if (balls >= 4) {
            walk()
        }
    }

    fun foul() {
        if (strikes == 2) {
            return
        } else {
            strike()
        }
    }

    fun nextBatter() {
        getBatterStats().atBats++

        strikes = 0
        balls = 0

        if (homeHitting) {
            playerUpHomeIndex = (playerUpHomeIndex + 1) % homeLineup.battingOrder.size
//            baseRunners.setBatter(homePlayers[playerUpHomeIndex])
        } else {
            playerUpAwayIndex = (playerUpAwayIndex + 1) % awayLineup.battingOrder.size
//            baseRunners.setBatter(awayPlayers[playerUpAwayIndex])
        }
    }

//    fun getRunner(base: Int): Int? {
////        return baseRunners.getRunner(base)
//    }

//    fun setBaseRunners() {
//
//    }
    fun getBatterStats() : PlayerGameStats {
        val activePlayerStats = getActivePlayerStats()
        if (!activePlayerStats.containsKey(getBatterName())) {
            activePlayerStats[getBatterName()] = PlayerGameStats()
        }
        return activePlayerStats[getBatterName()]!!
    }

    fun getLastBatterName() : String {
        if (homeHitting) {
            return homeLineup.getPlayerNameByIndex(playerUpHomeIndex - 1)
        }
        return awayLineup.getPlayerNameByIndex(playerUpAwayIndex - 1)
    }

    fun getBatterName() : String {
        if (homeHitting) {
            return homeLineup.getPlayerNameByIndex(playerUpHomeIndex)
        }
        return awayLineup.getPlayerNameByIndex(playerUpAwayIndex)
    }

    fun getPitcherName() : String {
        if (homeHitting) {
            return awayLineup.pitcher
        }
        return homeLineup.pitcher
    }

    fun endGame() {

    }

    fun getActivePlayerStats() : MutableMap<String, PlayerGameStats> {
        if (homeHitting) {
            return homePlayerStats
        }
        return awayPlayerStats
    }
}
