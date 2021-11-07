package com.example.calculator.domain

import com.example.calculator.domain.entity.ResultPanelType


interface SettingsDao {

    suspend fun setResultPanelType(resultPanelType: ResultPanelType)

    suspend fun getResultPanelType(): ResultPanelType

    suspend fun getAnswerPrecision(): Int

    suspend fun setAnswerPrecision(answerPrecision: Int)

    fun getResultAccuracy(): Int

    fun setVibrationForce(force: Int)

    fun getVibrationForce(): Int

}
