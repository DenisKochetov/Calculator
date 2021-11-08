package com.example.calculator.domain
import com.example.calculator.domain.entity.FormatResultEnum
import net.objecthunter.exp4j.ExpressionBuilder

fun calculateExpression(expression: String, formatResultType: FormatResultEnum?): String{

    val ex = ExpressionBuilder(expression).build()

    val answer = ex.evaluate()

    val result = when (formatResultType) {
        FormatResultEnum.ZERO -> String.format("%.1f", answer).toDouble()
        FormatResultEnum.ONE -> String.format("%.2f", answer).toDouble()
        FormatResultEnum.TWO -> String.format("%.3f", answer).toDouble()
        FormatResultEnum.MANY -> answer
        null -> answer
    }
    return result.toString()
}