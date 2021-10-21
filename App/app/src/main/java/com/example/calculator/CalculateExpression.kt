package com.example.calculator
import kotlinx.android.synthetic.main.main_activity.*
import net.objecthunter.exp4j.ExpressionBuilder

fun calculateExpression(expression: String): String{

    val ex = ExpressionBuilder(expression).build()
    val answer = ex.evaluate().toString()
    return answer
}