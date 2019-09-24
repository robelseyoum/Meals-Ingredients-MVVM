package com.robelseyoum3.mealsproject.viewmodel.detailmealsviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.robelseyoum3.mealsproject.repository.DetialMealRepository

class DetailMealViewModelFactory(private val detailMealRepository: DetialMealRepository)
    : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailMealViewModel(detailMealRepository) as T
    }
}
