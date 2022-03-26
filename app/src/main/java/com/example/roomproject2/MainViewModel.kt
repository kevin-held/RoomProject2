package com.example.roomproject2

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel (val dao: DonutDao): ViewModel() {
    var newDonutsAte = ""
    private val lastDonut : LiveData<Donut> = dao.getLast()
    val lastDonutsAte = Transformations.map(lastDonut){
            last->getDonuts((lastDonut))
    }

    fun getDonuts(donut: LiveData<Donut>): String {
        if (lastDonut != null && lastDonut.value != null) {
            return "Donuts Ate Yesterday: " + lastDonut.value!!.donutsAte
        } else {
            return "Donuts Ate Yesterday: 0"
        }
    }

    fun addDonut(){
        viewModelScope.launch {
            val donut = Donut()
            donut.donutsAte = newDonutsAte
            dao.insert(donut)
        }
    }
}