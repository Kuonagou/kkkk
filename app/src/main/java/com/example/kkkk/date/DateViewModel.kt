package com.example.kkkk.date

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.kkkk.data.DateActivity
import com.example.kkkk.data.DateRepository
import kotlinx.coroutines.launch
import java.util.Date

class DateViewModel(private val repository: DateRepository) : ViewModel() {
    private val _allDates = MutableLiveData<List<DateActivity>>()
    val allDates: LiveData<List<DateActivity>> = _allDates

    // LiveData pour les activités complétées, triées par date
    val completedActivities: LiveData<List<DateActivity>> = allDates.map{ dates ->
        dates.filter { it.dateDudate != null }
            .sortedBy { it.dateDudate }
    }

    // LiveData pour la date sélectionnée
    private val _selectedDate = MutableLiveData<DateActivity>()
    val selectedDate: LiveData<DateActivity> = _selectedDate

    init {
        loadAllDates()
    }

    private fun loadAllDates() {
        _allDates.value = repository.getAllDates()
    }

    fun getDateById(id: Int) {
        try {
            _selectedDate.value = repository.getDateById(id)
        } catch (e: Exception) {
            _selectedDate.value = null
        }
    }

    fun updateDateDate(id: Int, newDate: Date) {
        val currentDate = _selectedDate.value
        if (currentDate != null) {
            repository.saveDateDate(id, currentDate.title, currentDate.description, currentDate.color, newDate)
            getDateById(id)
            loadAllDates()
        }
    }
}