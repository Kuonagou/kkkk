package com.example.kkkk.poesie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kkkk.data.Poeme
import com.example.kkkk.data.PoemeRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class PoemeViewModel(private val repository: PoemeRepository) : ViewModel() {

    private val _selectedPoeme = MutableLiveData<Poeme?>()
    val selectedPoeme: LiveData<Poeme?> = _selectedPoeme

    private val _drawnPoems = MutableLiveData<List<Poeme>>(emptyList())
    val drawnPoems: LiveData<List<Poeme>> = _drawnPoems

    private val _lastDrawDate = MutableLiveData<String?>()
    val lastDrawDate: LiveData<String?> = _lastDrawDate

    init {
        loadPoems()
    }

    private fun loadPoems() {
        _drawnPoems.value = repository.getTakenPoems()
        _lastDrawDate.value = repository.getLastDrawDate()
    }


    fun getRandomPoeme() {
        viewModelScope.launch {
            val currentDate = getCurrentDate()

            // Vérifier si un poème a déjà été tiré aujourd'hui
            if (_lastDrawDate.value == currentDate) {
                return@launch
            }

            // Vérifier que la date est après le 1er mars
            val firstMarch = "01-03-${Calendar.getInstance().get(Calendar.YEAR)}"
            if (currentDate < firstMarch) {
                return@launch
            }

            val poeme = repository.getRandomAvailablePoem()
            poeme?.let {
                _selectedPoeme.value = it
                markPoemeAsTaken(it.id, currentDate) // ✅ Marquer le poème comme tiré
            }
        }
    }

    private fun markPoemeAsTaken(id: Int, date: String) {
        repository.markPoemeAsTaken(id, date)
        _drawnPoems.value = repository.getTakenPoems()
        _lastDrawDate.value = date
    }

    private fun getCurrentDate(): String {
        val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        return sdf.format(Calendar.getInstance().time)
    }

    fun getPoemeById(id: Int): Poeme {
        return repository.getPoemeById(id)
    }
}