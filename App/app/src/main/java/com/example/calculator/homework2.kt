package com.example.сalculator

import java.lang.StrictMath.sqrt

fun main(){
//    test()

    }




//fun findMaxFigs(figures: List<Figure>, param: String){
//    val maxPerim = figures.maxOf { it.getAttribute(param)}
//    val maxPerim = figures.maxOf { it.perimeter }
//    println("Максимальный периметр: "+ maxPerim)
//    var listMaxPer = figures.filter{it.perimeter == maxPerim}
//    println("Фигуры с макс периметром: ")
//    for (fig in listMaxPer){
//        println(fig.name +" со сторонами "+ fig.listOfSides)
//    }
//
//}


abstract class Figure{
    abstract val listOfSides: List<Double>
    abstract val perimeter: Double
    abstract val square: Double
    abstract val name: String
}

class Square(
    override val listOfSides: List<Double>

): Figure(){
    init {
        require(listOfSides.size == 1){
            "одна значащая сторона должна быть"
        }
    }
    override val perimeter: Double = listOfSides.sum() * 4
    override val square: Double = listOfSides[0] * listOfSides[0]
    override val name = "Квадрат"
}
class Rectangle(
    override val listOfSides: List<Double>,

    ): Figure(){
    init {
        require(listOfSides.size == 2){
            "две значащих стороны должно быть"
        }
    }
    override val perimeter: Double = listOfSides.sum() * 2
    override val square: Double = listOfSides[0] * listOfSides[1]
    override val name: String = "Прямоугольник"
}

class Triangle(
    override val listOfSides: List<Double>,

    ): Figure(){
    init {
        require(listOfSides.size == 3){
            "три значащих сторон должно быть"
        }
        require((listOfSides[0] + listOfSides[1] > listOfSides[2]) &&
                (listOfSides[0] + listOfSides[2] > listOfSides[1]) &&
                (listOfSides[1] + listOfSides[2] > listOfSides[0])){
            "две стороны должны быть больше третьей"
        }
    }
    override val perimeter: Double = listOfSides.sum()
    override val square: Double = sqrt(perimeter/2.0 * (perimeter/2.0 - listOfSides[0]) * (perimeter/2.0 - listOfSides[1]) * (perimeter/2.0 - listOfSides[2]))
    override val name: String = "Треугольник"
}

fun test() {
    val figures = listOf(
        Square(listOf(3.0)),
        Rectangle(listOf(2.0, 4.0)),
        Triangle(listOf(4.0, 3.0, 5.0))
    )
    for (fig in figures) {
        println(fig.name + " с периметром: " + fig.perimeter + ", площадью: " + "%.2f".format(fig.square))
    }
    val sumSquare = figures.sumOf { it.square }
    println("Сумма площадей: " + "%.2f".format(sumSquare))

    val maxPerim = figures.maxOf { it.perimeter }
    println("Максимальный периметр: " + maxPerim)
    var listMaxPer = figures.filter { it.perimeter == maxPerim }
    println("Фигуры с макс периметром: ")
    for (fig in listMaxPer) {
        println(fig.name + " со сторонами: " + fig.listOfSides)
    }

    val maxSquare = figures.maxOf { it.square }
    println("Максимальная площаль: " + "%.2f".format(maxSquare))
    var listMaxSquare = figures.filter { it.square == maxSquare }
    println("Фигуры с макс площадью: ")
    for (fig in listMaxSquare) {
        println(fig.name + " со сторонами " + fig.listOfSides)
    }
}