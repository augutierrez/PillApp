package com.example.pillapp

import org.junit.Test

import org.junit.Assert.*
import java.util.*
import kotlin.concurrent.schedule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class PillUnitTests {

    @Test
    fun timeConsumptionLimit_set_and_check_false(){
        val pill = Pill(timeConsumptionLimit = 1.0)
        pill.takePill()
        assertFalse(pill.frequencyCheck())
    }

    @Test
    fun timeConsumptionLimit_set_and_check_true(){
        val pill = Pill(timeConsumptionLimit = 0.0)
        pill.takePill() //it won't check the time if you haven't taken the pill at least once today
        assertTrue(pill.frequencyCheck())
    }

    @Test fun timeConsumptionLimit_set_and_wait_check_true(){
        val pill = Pill(timeConsumptionLimit = .0028)
        pill.takePill()
        Timer("SettingUp", false).schedule(9000) {
            assertTrue(pill.frequencyCheck())
        }

    }


    @Test
    fun dailyConsumptionLimit_set_and_check_false(){
        val pill = Pill(dailyConsumptionLimit = 3)
        pill.takePill()
        pill.takePill()
        pill.takePill()
        assertFalse(pill.frequencyCheck())
    }

    @Test
    fun dailyConsumptionLimit_set_and_check_true(){
        val pill = Pill(dailyConsumptionLimit = 3)
        pill.takePill()
        pill.takePill()
        assertTrue(pill.frequencyCheck())
    }


    @Test
    fun take_pill_lastTaken_gets_updated_check(){
        val pill = Pill()
        pill.takePill()
        assertTrue(pill.lastTaken != null)
    }

    @Test
    fun lastTaken_null_check(){
        val pill = Pill()
        assertTrue(pill.lastTaken == null)
    }

    @Test
    fun can_take_default_check() {
        //should always be true by default
        val pill = Pill()
        assertTrue(pill.frequencyCheck())
    }

    @Test
    fun pill_creator(){
        val pill = Pill("Hi")
        assertTrue(pill.name.equals("Hi"))
    }


    @Test
    fun pill_dailyConsumptionLimit_default_check(){
        val pill = Pill("Hi")
        assertEquals(pill.dailyConsumptionLimit, -1)
    }

    @Test
    fun pill_dailyConsumptionLimit_change_default_check(){
        val pill = Pill("Hi")
        pill.dailyConsumptionLimit=2
        assertEquals(pill.dailyConsumptionLimit, 2)
    }
}