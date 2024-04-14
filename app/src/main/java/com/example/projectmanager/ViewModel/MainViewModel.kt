package com.example.projectmanager.ViewModel

import androidx.lifecycle.ViewModel
import com.example.projectmanager.model.Repository.MainRepository

class MainViewModel(val repository: MainRepository): ViewModel() {

    constructor():this(MainRepository())
    fun loadData() = repository.items

}