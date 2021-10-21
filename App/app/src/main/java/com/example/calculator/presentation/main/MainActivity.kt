package com.example.calculator.presentation.main

//import androidx.appcompat.app.AppCompatActivity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.calculator.R
import com.example.calculator.presentation.common.BaseActivity
import com.example.calculator.presentation.history.HistoryActivity
import com.example.calculator.presentation.settings.SettingsActivity
import kotlinx.android.synthetic.main.main_activity.*
import net.objecthunter.exp4j.ExpressionBuilder

open class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModels()
//    private val viewBinding by viewBinding(MainActivityBinding::bind)

//    private val getResult: ActivityResultLauncher<Int>
//        registerForActivityResult(Result()) {result ->
//
//        Toast.makeText(this, "result $result", Toast.LENGTH_SHORT).show()
//        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)


        btn_0.setOnClickListener { updateText("0") }
        btn_1.setOnClickListener { updateText("1") }
        btn_2.setOnClickListener { updateText("2") }
        btn_3.setOnClickListener { updateText("3") }
        btn_4.setOnClickListener { updateText("4") }
        btn_5.setOnClickListener { updateText("5") }
        btn_6.setOnClickListener { updateText("6") }
        btn_7.setOnClickListener { updateText("7") }
        btn_8.setOnClickListener { updateText("8") }
        btn_9.setOnClickListener { updateText("9") }
        btn_minus.setOnClickListener { updateText("-") }
        btn_plus.setOnClickListener { updateText("+") }
        btn_divide.setOnClickListener { updateText("/") }
        btn_multiply.setOnClickListener { updateText("*") }
        btn_left_bracket.setOnClickListener { updateText("(") }
        btn_right_bracket.setOnClickListener { updateText(")") }
        btn_dot.setOnClickListener { updateText(".") }
        btn_AC.setOnClickListener { deleteText() }
        btn_back.setOnClickListener { backText() }
        btn_equal.setOnClickListener { doMath() }

//        listOf(
//            viewBinding.btn_0,
//            viewBinding.btn_1,
//            viewBinding.btn_2,
//            viewBinding.btn_3,
//            viewBinding.btn_4,
//            viewBinding.btn_5,
//            viewBinding.btn_6,
//            viewBinding.btn_7,
//            viewBinding.btn_8,
//            viewBinding.btn_9,
//            viewBinding.btn_minus,
//            viewBinding.btn_plus,
//            viewBinding.btn_divide,
//            viewBinding.btn_multiply,
//            viewBinding.btn_left_bracket,
//            viewBinding.btn_right_bracket,
//            viewBinding.btn_dot
//        ).forEachIndexed{index, textView ->
//            textView.setOnClickListener {viewModel.onNumberClick(index)}
//
//        }
        viewModel.expressionState.observe(this) { state ->
            calculation.setText(state)
        }

        viewModel.resultState.observe(this){state ->
            result.setText(state)
        }

        main_activity_settings.setOnClickListener { openSettings() }


    }

    override fun <T> viewModels(t: T): T {
        TODO("Not yet implemented")
    }

    private fun updateText(str: String) {
        if (result.text != "" && result.text != "Error") {
            calculation.text = result.text
            result.text = ""
        }
        calculation.append(str)
    }

    fun deleteText() {
        calculation.text = ""
        result.text = ""
    }

    fun backText() {
        if (calculation.text.isNotEmpty()) {
            calculation.text = calculation.text.substring(0, calculation.text.length - 1)
            result.text = ""

        }
    }

    fun doMath() {
        try {
            val ex = ExpressionBuilder(calculation.text.toString()).build()
            val answer = ex.evaluate()
            val longRes = answer.toLong()
            if (answer == longRes.toDouble()) {
                result.text = longRes.toString()
            } else {
                result.text = answer.toString()
            }


        } catch (e: Exception) {
            result.text = "Error"

        }
    }

    fun openSettings() {
        Toast.makeText(this, "Открытие настроек", Toast.LENGTH_SHORT).show()
        Intent(this, SettingsActivity::class.java).also{
            startActivity(it)
        }

    }

    private fun openHistory() {
        startActivity(Intent(this, HistoryActivity::class.java))
    }

}


