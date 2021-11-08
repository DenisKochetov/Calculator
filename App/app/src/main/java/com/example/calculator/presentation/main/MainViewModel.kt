package com.example.calculator.presentation.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculator.domain.HistoryRepository
import com.example.calculator.domain.SettingsDao
import com.example.calculator.domain.entity.ForceVibrationTypeEnum
import com.example.calculator.domain.entity.FormatResultEnum
import com.example.calculator.domain.entity.ResultPanelType
import kotlinx.coroutines.launch
import net.objecthunter.exp4j.ExpressionBuilder
import kotlin.math.pow
import kotlin.math.sqrt

class MainViewModel(
    private val settingsDao: SettingsDao,
    private val historyRepository: HistoryRepository
): ViewModel() {

    private var expression: String = ""

    private val _expressionState = MutableLiveData<String>()
    val expressionState: LiveData<String> = _expressionState

    private var result: String = ""
    private val _resultState = MutableLiveData<String>()
    val resultState: LiveData<String> = _resultState

    private val _resultPanelState = MutableLiveData<ResultPanelType>(ResultPanelType.RIGHT)
    val resultPanelState: LiveData<ResultPanelType> = _resultPanelState

    private val _formatResultState = MutableLiveData(FormatResultEnum.MANY)
    val formatResultState: LiveData<FormatResultEnum> = _formatResultState

    private val _forceVibrationState =
        MutableLiveData<ForceVibrationTypeEnum>(ForceVibrationTypeEnum.SMALL)
    val forceVibrationState: LiveData<ForceVibrationTypeEnum> = _forceVibrationState

    init {
        viewModelScope.launch {
            _resultPanelState.value = settingsDao.getResultPanelType()
            _formatResultState.value = settingsDao.getFormatResultType()
            _forceVibrationState.value = settingsDao.getForceVibrationType()
        }
    }


    fun onNumberClick(number: Int){
        if (result != "" && result != "Error") {
            expression = result
            _expressionState.value = result
            result = ""
            _resultState.value = result
        }
        expression  += number.toString()
        _expressionState.value = expression
    }

    fun onNumberClick(command: String){
        if (result != "" && result != "Error") {
            expression = result
            _expressionState.value = result
            result = ""
            _resultState.value = result
        }
        expression  += command.toString()
        _expressionState.value = expression
    }

    fun deleteText() {
        expression = ""
        result = ""
        _expressionState.value = expression
        _resultState.value = result
    }

    fun backText() {
        if (expression.isNotEmpty()) {
            expression = expression.substring(0, expression.length - 1)
            _expressionState.value = expression
            result = ""
            _resultState.value = result

        }
    }

    fun doMath() {
            val formatResultType : FormatResultEnum? = _formatResultState.value
        try{
            val ex = ExpressionBuilder(expression).build()
            val answer = ex.evaluate()
            val longRes = answer.toLong()
            if (answer == longRes.toDouble()) {
                result = longRes.toString()
            } else {
                result = answer.toString()
            }

            result = when (formatResultType) {
                FormatResultEnum.ZERO -> String.format("%0f", answer)
                FormatResultEnum.ONE -> String.format("%.1f", answer)
                FormatResultEnum.TWO -> String.format("%.2f", answer)
                FormatResultEnum.MANY -> answer.toString()
                null -> answer.toString()
            }


        } catch (e: Exception) {
            result = "Error"

        }
        _resultState.value = result

    }

    fun onSqrtClick() {
        if (result != "" && result != "Error") {
            expression = result
            _expressionState.value = result
            result = ""
            _resultState.value = result
        }
        val formatResultType : FormatResultEnum? = _formatResultState.value
        try{
            val ex = ExpressionBuilder(expression).build()
            val answer = sqrt(ex.evaluate())
            val longRes = answer.toLong()
            if (answer == longRes.toDouble()) {
                result = longRes.toString()
            } else {
                result = answer.toString()
            }

            result = when (formatResultType) {
                FormatResultEnum.ZERO -> String.format("%0f", answer)
                FormatResultEnum.ONE -> String.format("%.1f", answer)
                FormatResultEnum.TWO -> String.format("%.2f", answer)
                FormatResultEnum.MANY -> answer.toString()
                null -> answer.toString()
            }


        } catch (e: Exception) {
            result = "Error"

        }
        _resultState.value = result

    }

    fun onSqrClick() {
        if (result != "" && result != "Error") {
            expression = result
            _expressionState.value = result
            result = ""
            _resultState.value = result
        }
        val formatResultType : FormatResultEnum? = _formatResultState.value
        try{
            val ex = ExpressionBuilder(expression).build()
            val answer = ex.evaluate().pow(2)
            val longRes = answer.toLong()
            if (answer == longRes.toDouble()) {
                result = longRes.toString()
            } else {
                result = answer.toString()
            }

            result = when (formatResultType) {
                FormatResultEnum.ZERO -> String.format("%0f", answer)
                FormatResultEnum.ONE -> String.format("%.1f", answer)
                FormatResultEnum.TWO -> String.format("%.2f", answer)
                FormatResultEnum.MANY -> answer.toString()
                null -> answer.toString()
            }


        } catch (e: Exception) {
            result = "Error"

        }
        _resultState.value = result

    }

//    override suspend fun getFormatResultType(): FormatResultEnum = withContext(Dispatchers.IO) {
//        preferences.getString(FORMAT_RESULT_TYPE_KEY, null)
//            ?.let { FormatResultEnum.valueOf(it) } ?: FormatResultEnum.MANY
//    }
//
//    override suspend fun setFormatResultType(formatResultType: FormatResultTypeEnum) =
//        withContext(Dispatchers.IO) {
//            preferences.edit().putString(FORMAT_RESULT_TYPE_KEY, formatResultType.name).apply()
//        }



    override fun onCleared() {
        super.onCleared()
        Log.d("MainViewMoodel", "onCleared")
    }

    companion object {

        private const val RESULT_PANEL_TYPE_KEY = "RESULT_PANEL_TYPE_KEY"
        private const val FORMAT_RESULT_TYPE_KEY = "FORMAT_RESULT_TYPE_KEY"
    }
}


