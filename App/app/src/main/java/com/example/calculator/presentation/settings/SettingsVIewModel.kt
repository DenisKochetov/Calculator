package com.example.calculator.presentation.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//import com.example.calculator.ResultPanelType

class SettingsViewModel: ViewModel() {
    private val _resultPanelState = MutableLiveData<ResultPanelType>()
    val resultPanelState: LiveData<ResultPanelType> = _resultPanelState

//    private val _openResultPanelAction = SingleLiveEvent<ResultPanelType>()
//    val openResultPanelAction

    fun onResultPanelTypeChanged(resultPanelType: ResultPanelType){

    }

//    operator fun getValue(settingsActivity: SettingsActivity, property: KProperty<*>): Any {
//
//    }
}

