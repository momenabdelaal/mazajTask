package com.mazaj.task.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mazaj.task.network.AppRepository
import com.mazaj.task.view.ui.home.HomeViewModel

class ViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(AppRepository()) as T
            }

            else -> throw IllegalArgumentException("Unknown class name")
        }
    }

}
