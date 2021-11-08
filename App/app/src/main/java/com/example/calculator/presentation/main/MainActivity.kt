package com.example.calculator.presentation.main

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.activity.result.launch
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.calculator.R
import com.example.calculator.data.db.history.HistoryResult
import com.example.calculator.databinding.MainActivityBinding
import com.example.calculator.di.HistoryRepositoryProvider
import com.example.calculator.di.SettingsDaoProvider
import com.example.calculator.domain.entity.ResultPanelType
import com.example.calculator.presentation.settings.SettingsActivity
//import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {


    private val viewBinding by viewBinding(MainActivityBinding::bind)
    private val viewModel by viewModels<MainViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(SettingsDaoProvider.getDao(this@MainActivity),
                    HistoryRepositoryProvider.get(this@MainActivity)
                ) as T
            }
        }
    }
    private val resultLauncher = registerForActivityResult(HistoryResult()) { item ->
        viewModel.onHistoryResult(item)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)


        viewBinding.btnAC?.setOnClickListener{viewModel.deleteText()}
        viewBinding.btnBack?.setOnClickListener{viewModel.backText()}
        viewBinding.btnEqual?.setOnClickListener{viewModel.doMath()}
        viewBinding.btnSqrt?.setOnClickListener{viewModel.onSqrtClick()}
        viewBinding.btnPower?.setOnClickListener{viewModel.onSqrClick()}

        listOf(
            viewBinding.btn0,
            viewBinding.btn1,
            viewBinding.btn2,
            viewBinding.btn3,
            viewBinding.btn4,
            viewBinding.btn5,
            viewBinding.btn6,
            viewBinding.btn7,
            viewBinding.btn8,
            viewBinding.btn9,
            viewBinding.btnPlus,
            viewBinding.btnMinus,
            viewBinding.btnLeftBracket,
            viewBinding.btnRightBracket,
            viewBinding.btnMultiply,
            viewBinding.btnDivide,
            viewBinding.btnDot
        ).forEach{command ->
            val text = command?.getText().toString()
            command?.setOnClickListener { viewModel.onNumberClick(text) }
        }



        viewModel.expressionState.observe(this) { state ->
            viewBinding.expression?.text = state
        }

        viewModel.resultState.observe(this){state ->
            viewBinding.result.text = state
        }

        viewModel.resultPanelState.observe(this) {
            with(viewBinding.result) {
                gravity = when (it) {
                    ResultPanelType.LEFT -> Gravity.START.or(Gravity.CENTER_VERTICAL)
                    ResultPanelType.RIGHT -> Gravity.END.or(Gravity.CENTER_VERTICAL)
                    ResultPanelType.HIDE -> viewBinding.result.gravity
                }
                isVisible = it != ResultPanelType.HIDE
            }
        }

//        viewModel.forceVibrationState.observe(this) { state ->
//            forceVibrationValue = when (state) {
//                NO -> VibrationMSTypes.NO.force
//                SMALL -> VibrationMSTypes.SMALL.force
//                MEDIUM -> VibrationMSTypes.MEDIUM.force
//                STRONG -> VibrationMSTypes.STRONG.force
//            }
//        }


        viewBinding.mainActivitySettings?.setOnClickListener {
            openSettings()
        }

        viewBinding.mainActivityHistory?.setOnClickListener{
            openHistory()
        }

    }


    private fun updateText(str: String) {
        if (viewBinding.result.text != "" && viewBinding.result.text != "Error") {
            viewBinding.expression.text = viewBinding.result.text
            viewBinding.result.text = ""
        }
        viewBinding.expression.append(str)
    }


    private fun openSettings() {
        Toast.makeText(this, "Открытие настроек", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, SettingsActivity::class.java))

    }

    private fun openHistory() {
        resultLauncher.launch()
    }

}


