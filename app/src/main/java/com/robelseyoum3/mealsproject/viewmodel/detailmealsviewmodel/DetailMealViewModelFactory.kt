package com.robelseyoum3.mealsproject.viewmodel.detailmealsviewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.robelseyoum3.mealsproject.network.CategoryRequestInterface

class DetailMealViewModelFactory
    (private val categoryRequestInterface: CategoryRequestInterface, private val application: Application)
    : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailMealViewModel(
            categoryRequestInterface,
            application
        ) as T
    }
}

