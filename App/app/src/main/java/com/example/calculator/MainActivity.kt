package com.example.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

open class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_0.setOnClickListener{updateText("0")}
        btn_1.setOnClickListener{updateText("1")}
        btn_2.setOnClickListener{updateText("2")}
        btn_3.setOnClickListener{updateText("3")}
        btn_4.setOnClickListener{updateText("4")}
        btn_5.setOnClickListener{updateText("5")}
        btn_6.setOnClickListener{updateText("6")}
        btn_7.setOnClickListener{updateText("7")}
        btn_8.setOnClickListener{updateText("8")}
        btn_9.setOnClickListener{updateText("9")}
        btn_minus.setOnClickListener{updateText("-")}
        btn_plus.setOnClickListener{updateText("+")}
        btn_divide.setOnClickListener{updateText("/")}
        btn_multiply.setOnClickListener{updateText("*")}
        btn_left_bracket.setOnClickListener{updateText("(")}
        btn_right_bracket.setOnClickListener{updateText(")")}
        btn_dot.setOnClickListener{updateText(".")}
        btn_AC.setOnClickListener{deleteText()}
        btn_back.setOnClickListener{backText() }
        btn_equal.setOnClickListener{doMath() }

    }
    private fun openSettings(){

    }

    private fun updateText(str: String){
        if (result.text != "" && result.text != "Error"){
            calculation.text = result.text
            result.text = ""
        }
        calculation.append(str)
    }
    fun deleteText(){
        calculation.text = ""
        result.text = ""
    }
    fun backText(){
        if (calculation.text.isNotEmpty()){
            calculation.text = calculation.text.substring(0, calculation.text.length-1)
            result.text = ""

        }
    }
    fun doMath(){
        try{
            val ex = ExpressionBuilder(calculation.text.toString()).build()
            val answer = ex.evaluate()
            val longRes = answer.toLong()
            if (answer == longRes.toDouble()){
                result.text = longRes.toString()
            }
            else{
                result.text = answer.toString()
            }



        }
        catch (e:Exception){
            result.text = "Error"

        }
    }

}


