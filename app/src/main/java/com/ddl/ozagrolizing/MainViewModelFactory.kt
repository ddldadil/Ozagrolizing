package com.ddl.ozagrolizing

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModelFactory(context: Context) : ViewModelProvider.Factory {

    val db = DataBase.getDataBase(context)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(db=db) as T
    }
}