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
import com.example.calculator.presentation.history.HistoryItem
import kotlinx.coroutines.launch
import net.objecthunter.exp4j.ExpressionBuilder
import kotlin.math.floor
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
        updateExpression()
        expression  += number.toString()
        _expressionState.value = expression
    }

    fun onNumberClick(command: String){
        updateExpression()

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
        print(formatResultType)
        Log.d("type", formatResultType.toString())

        try{
            val ex = ExpressionBuilder(expression).build()
            val answer = ex.evaluate()
            val longRes = answer.toLong()
            if (answer == longRes.toDouble()) {
                result = longRes.toString()
            } else {
                result = answer.toString()
            }
            Log.d("type", result.toString())

            if (floor(answer) == answer) {
                result.toInt().toString()
            } else {

                result = when (formatResultType) {
                    FormatResultEnum.ONE -> String.format("%.1f", answer)
                    FormatResultEnum.TWO -> String.format("%.2f", answer)
                    FormatResultEnum.THREE -> String.format("%.3f", answer)
                    FormatResultEnum.MANY -> answer.toString()
                    null -> answer.toString()
                }
            }
            Log.d("type", String.format("%.0f", answer))
            Log.d("type", String.format("%.1f", answer))
            Log.d("type", String.format("%.2f", answer))


        } catch (e: Exception) {
            result = "Error"

        }
        _resultState.value = result
        viewModelScope.launch{
            historyRepository.add(HistoryItem(expression, result))
        }

    }

    fun onSqrtClick() {
        updateExpression()
        val formatResultType : FormatResultEnum? = _formatResultState.value
        try{
            val ex = ExpressionBuilder(expression).build()
            val answer = sqrt(ex.evaluate())
            if (floor(answer) == answer) {
                result.toInt().toString()
            } else {

                result = when (formatResultType) {
                    FormatResultEnum.ONE -> String.format("%.1f", answer)
                    FormatResultEnum.TWO -> String.format("%.2f", answer)
                    FormatResultEnum.THREE -> String.format("%.3f", answer)
                    FormatResultEnum.MANY -> answer.toString()
                    null -> answer.toString()
                }
            }


        } catch (e: Exception) {
            result = "Error"

        }
        _resultState.value = result
        viewModelScope.launch{
            historyRepository.add(HistoryItem(expression, result))
        }

    }

    fun onSqrClick() {
        updateExpression()
        val formatResultType : FormatResultEnum? = _formatResultState.value
        try{
            val ex = ExpressionBuilder(expression).build()
            val answer = ex.evaluate().pow(2)
            val longRes = answer.toLong()
            if (answer == longRes.toDouble()) {
                result = longRes.toString()
            }
            if (floor(answer) == answer) {
                result.toInt().toString()
            } else {

                result = when (formatResultType) {
                    FormatResultEnum.ONE -> String.format("%.1f", answer)
                    FormatResultEnum.TWO -> String.format("%.2f", answer)
                    FormatResultEnum.THREE -> String.format("%.3f", answer)
                    FormatResultEnum.MANY -> answer.toString()
                    null -> answer.toString()
                }
            }


        } catch (e: Exception) {
            result = "Error"

        }
        _resultState.value = result
        viewModelScope.launch{
            historyRepository.add(HistoryItem(expression, result))
        }

    }



    fun updateExpression() {
        if (result != "" && result != "Error") {
            expression = result
            _expressionState.value = result
            result = ""
            _resultState.value = result
        }
    }


    fun onHistoryResult(item: HistoryItem?) {
        if (item != null) {
            expression = item.expression
            _expressionState.value = expression
            _resultState.value = item.result
        }
    }


    override fun onCleared() {
        super.onCleared()
        Log.d("MainViewMoodel", "onCleared")
    }


    companion object {

        private const val RESULT_PANEL_TYPE_KEY = "RESULT_PANEL_TYPE_KEY"
        private const val FORMAT_RESULT_TYPE_KEY = "FORMAT_RESULT_TYPE_KEY"
    }

    private fun resultUpdate() {
        doMath()
        _resultState.value = result
    }

//    fun onStart() {
//        viewModelScope.launch {
//            _resultPanelState.value = settingsDao.getResultPanelType()
//            _formatResultState.value = settingsDao.getFormatResultType()
//            resultUpdate()
//        }
//    }
}


