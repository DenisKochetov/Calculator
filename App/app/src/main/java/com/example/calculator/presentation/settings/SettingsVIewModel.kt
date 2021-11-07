package com.example.calculator.presentation.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculator.domain.entity.ResultPanelType

//import com.example.calculator.ResultPanelType

class SettingsViewModel: ViewModel() {
    private val _resultPanelState = MutableLiveData<ResultPanelType>(ResultPanelType.LEFT)
    val resultPanelState: LiveData<ResultPanelType> = _resultPanelState

    private val _openResultPanelAction = SingleLiveEvent<ResultPanelType>()
    val openResultPanelAction: LiveData<ResultPanelType> = _openResultPanelAction

    fun onResultPanelTypeChanged(resultPanelType: ResultPanelType){
        _resultPanelState.value = resultPanelType

    }


    fun onResultPanelTypeClicked(){
        _openResultPanelAction.value = _resultPanelState.value

    }



//    operator fun getValue(settingsActivity: SettingsActivity, property: KProperty<*>): Any {
//
//    }
}

