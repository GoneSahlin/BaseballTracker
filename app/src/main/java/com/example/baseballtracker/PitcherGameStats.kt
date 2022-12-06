package com.example.baseballtracker

class PitcherGameStats(pitchTypes: Array<String>, var inningsPerGame: Int) : java.io.Serializable {
    var pitches = 0
    var strikes = 0
    var balls = 0
    var outs = 0
    var earnedRuns = 0
    var hits = 0
    var walks = 0
    var strikeouts = 0
    var pitchTypeStrikes = mutableMapOf<String,Int>()
    var pitchTypeBalls = mutableMapOf<String,Int>()

    init {
        for (pitchType in pitchTypes) {
            pitchTypeStrikes[pitchType] = 0
            pitchTypeBalls[pitchType] = 0
        }
    }

    fun addStrike(pitchType: String) {
        strikes++
        pitches++
        pitchTypeStrikes[pitchType] = pitchTypeStrikes[pitchType]!! + 1
    }

    fun addBall(pitchType: String) {
        pitches++
        balls++
        pitchTypeBalls[pitchType] = pitchTypeBalls[pitchType]!! + 1
    }

    fun getEra() : String {
        var era = 0.0
        if (outs != 0) {
            return (earnedRuns * inningsPerGame * 3.0 / outs).toString()
        }
        else {
            return "0"
        }
    }
}