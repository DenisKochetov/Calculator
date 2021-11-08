package com.example.calculator.domain

import com.example.calculator.domain.entity.ForceVibrationTypeEnum
import com.example.calculator.domain.entity.FormatResultEnum
import com.example.calculator.domain.entity.ResultPanelType


interface SettingsDao {

    suspend fun setResultPanelType(resultPanelType: ResultPanelType)

    suspend fun getResultPanelType(): ResultPanelType

    suspend fun getFormatResultType(): FormatResultEnum

    suspend fun setFormatResultType(formatResultType: FormatResultEnum)

    suspend fun getForceVibrationType(): ForceVibrationTypeEnum

    suspend fun setForceVibrationType(forceVibrationType: ForceVibrationTypeEnum)

}
