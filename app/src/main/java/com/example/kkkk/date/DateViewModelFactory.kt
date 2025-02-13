package com.example.kkkk.date

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kkkk.data.DateRepository

class DateViewModelFactory(private val repository: DateRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DateViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DateViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}