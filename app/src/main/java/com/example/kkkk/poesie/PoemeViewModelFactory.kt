package com.example.kkkk.poesie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kkkk.data.PoemeRepository

class PoemeViewModelFactory(private val repository: PoemeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PoemeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PoemeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
