package com.thepparat.unitconverterapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.thepparat.unitconverterapplication.data.ConverterRepository

@Suppress("UNCHECKED_CAST")
class ConverterViewModelFactory(private val repository: ConverterRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ConverterViewModel(repository) as T
    }
}