package com.example.pillapp

import java.util.*

/**
 * Pill class is the object used to keep track of the users existing medicines.
 * dailyConsumptionLimit and timeConsumptionLimit are the medicine's consumption limits and are represented in hours.
 */
class Pill (var name: String = "N/A", var dailyConsumptionLimit: Int = -1, var timeConsumptionLimit: Double = -1.0){
    // -1 will represent no existing limit
    var lastTaken : Calendar ?= null
    var timesTakenToday : Int = 0
    //TODO figure out how we will reset timesTakenToday to 0


    /**
     * frequencyCheck is responsible for keeping track of when it's okay to drink pills.
     * Pills have different consumption limitations: as needed, once daily, or multiple times a day (with some time apart).
     * Therefore two inputs must be collected: daily consumption count limit and time between pill consumption limit.
     */
    fun frequencyCheck(): Boolean {
        if(dailyConsumptionLimit == -1 && timeConsumptionLimit == -1.0){
            return true
        }
        if(dailyConsumptionLimit != -1 && timesTakenToday >= dailyConsumptionLimit){
            return false
        }
        //last taken + timeConsumptionLimit metric
        if(timeConsumptionLimit != -1.0 && lastTaken != null){
            val lt = lastTaken!!.timeInMillis
            val now = Calendar.getInstance().timeInMillis
            // if lastTaken + time constraint is not less ms than now, not enough time has passed by
            if(lt + timeConsumptionLimit*3600000 > now){
                return false
            }
        }

        //TODO add lastTaken as part of the metric
        return true
    }

    fun takePill(){
        if(frequencyCheck()){
            lastTaken = Calendar.getInstance()
            timesTakenToday++
        }
    }

}