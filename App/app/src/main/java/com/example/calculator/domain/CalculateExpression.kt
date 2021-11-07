package com.example.calculator.domain
import net.objecthunter.exp4j.ExpressionBuilder

fun calculateExpression(expression: String): String{

    val ex = ExpressionBuilder(expression).build()
    val answer = ex.evaluate().toString()
    return answer
}