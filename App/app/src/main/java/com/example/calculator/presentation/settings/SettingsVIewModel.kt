package com.example.calculator.presentation.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculator.domain.SettingsDao
import com.example.calculator.domain.entity.ForceVibrationTypeEnum
import com.example.calculator.domain.entity.FormatResultEnum
import com.example.calculator.domain.entity.ResultPanelType
import kotlinx.coroutines.launch


class SettingsViewModel(
    private val settingsDao: SettingsDao
) : ViewModel() {

    private val _resultPanelState = MutableLiveData<ResultPanelType>(ResultPanelType.LEFT)
    val resultPanelState: LiveData<ResultPanelType> = _resultPanelState

    private val _openResultPanelAction = SingleLiveEvent<ResultPanelType>()
    val openResultPanelAction: LiveData<ResultPanelType> = _openResultPanelAction


    private val _formatResultState = MutableLiveData<FormatResultEnum>(FormatResultEnum.MANY)
    val formatResultState: LiveData<FormatResultEnum> = _formatResultState

    private val _openFormatResultAction = SingleLiveEvent<FormatResultEnum>()
    val openFormatResultAction: LiveData<FormatResultEnum> = _openFormatResultAction


    private val _forceVibrationState = MutableLiveData<ForceVibrationTypeEnum>(
        ForceVibrationTypeEnum.SMALL
    )
    val forceVibrationState: LiveData<ForceVibrationTypeEnum> = _forceVibrationState

    private val _openForceVibrationAction = SingleLiveEvent<ForceVibrationTypeEnum>()
    val openForceVibrationAction: LiveData<ForceVibrationTypeEnum> = _openForceVibrationAction


    init {
        viewModelScope.launch {
            _resultPanelState.value = settingsDao.getResultPanelType()
            _formatResultState.value = settingsDao.getFormatResultType()
            _forceVibrationState.value = settingsDao.getForceVibrationType()
        }
    }


    fun onFormatResultChanged(formatResultType: FormatResultEnum) {
        _formatResultState.value = formatResultType
        viewModelScope.launch {
            settingsDao.setFormatResultType(formatResultType)
        }
    }

    fun onFormatResultPanelTypeClicked() {
        _openFormatResultAction.value = _formatResultState.value
    }


    fun onResultPanelTypeChanged(resultPanelType: ResultPanelType) {
        _resultPanelState.value = resultPanelType
        viewModelScope.launch {
            settingsDao.setResultPanelType(resultPanelType)
        }
    }

    fun onResultPanelTypeClicked() {
        _openResultPanelAction.value = _resultPanelState.value
    }


    fun onForceVibrationChanged(forceVibrationType: ForceVibrationTypeEnum) {
        _forceVibrationState.value = forceVibrationType
        viewModelScope.launch {
            settingsDao.setForceVibrationType(forceVibrationType)
        }
    }

    fun onForceVibrationPanelTypeClicked() {
        _openForceVibrationAction.value = _forceVibrationState.value
    }
}

