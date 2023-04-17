package com.valokafor.prontotrack

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.math.RoundingMode

class TrackerViewModel():ViewModel() {
    var stepCounter = mutableStateOf(0)
        private set

    var totalCalorie = mutableStateOf(0)
        private set

    var distanceInMeters = mutableStateOf("0 meter")
        private set


    fun onIncreaseStep(value: Int) {
        updateCounter(value)
        val distance = stepCounter.value * 0.762
        updateDistance(distance)
    }

    fun updateCalorie(value: Int) {
        totalCalorie.value += value
    }

    private fun updateCounter(value: Int) {
        stepCounter.value += value
    }

    fun updateDistance(distance: Double) {
        val roundedDistance = distance.toBigDecimal().setScale(2, RoundingMode.DOWN)
        distanceInMeters.value = "$roundedDistance meters"
    }

    fun onEatPizza() {
        updateCalorie(100)
    }

    fun onDrinkWater() {
        updateCalorie(0)
    }

    fun onEatApple() {
        updateCalorie(30)
    }

    fun onDrinkCoffee() {
        updateCalorie(5)
    }

    fun onResetState() {
        totalCalorie.value = 0
        stepCounter.value = 0
        updateDistance(0.0)
    }
}