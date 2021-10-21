package com.example.calculator

import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test

class CalculateExpressionKtTest {

    @Test
    fun testPlus(){
        val expression = "2+2"
        val result = "4"
        Assert.assertEquals(result, calculateExpression(expression))
    }

    @Test
    fun testSubstraction(){
        val expression = "4-2*2"
        val result = "0"
        Assert.assertEquals(result, calculateExpression(expression))
    }
}