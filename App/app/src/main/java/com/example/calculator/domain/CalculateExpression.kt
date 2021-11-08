package com.example.calculator.domain
import com.example.calculator.domain.entity.FormatResultEnum
import net.objecthunter.exp4j.ExpressionBuilder
import kotlin.math.floor



fun calculateExpression(expression: String, formatResultType: FormatResultEnum?): String{

    try {
        if (expression.isBlank()) return ""

        // пока последний символ не число, убираем последний элемент строки
        var formattedExpression = expression
        while (!formattedExpression.last().isDigit()) {
            formattedExpression = formattedExpression.dropLast(1)
        }

        val ex = ExpressionBuilder(formattedExpression).build()
        val solution = ex.evaluate()


        val result = when (formatResultType) {
            FormatResultEnum.ONE -> String.format("%.0f", solution).toDouble()
            FormatResultEnum.TWO -> String.format("%.2f", solution).toDouble()
            FormatResultEnum.THREE -> String.format("%.1f", solution).toDouble()
            FormatResultEnum.MANY -> solution
            null -> solution
        }

        return if (floor(result) == result) {
            result.toInt().toString()
        } else {
            result.toString()
        }
    } catch (exc: NumberFormatException) {
        return "Ошибка"
    }
}