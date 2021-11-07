package com.example.calculator.presentation.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculator.domain.HistoryRepository
import com.example.calculator.domain.SettingsDao
import net.objecthunter.exp4j.ExpressionBuilder

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
        try {
            val ex = ExpressionBuilder(expression).build()
            val answer = ex.evaluate()
            val longRes = answer.toLong()
            if (answer == longRes.toDouble()) {
                result = longRes.toString()
            } else {
                result = answer.toString()
            }


        } catch (e: Exception) {
            result = "Error"

        }
        _resultState.value = result

    }



    override fun onCleared() {
        super.onCleared()
        Log.d("MainViewMoodel", "onCleared")
    }


}