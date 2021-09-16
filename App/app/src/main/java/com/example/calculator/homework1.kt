package com.example.calculator

fun main(){
    sumList()

}

fun sumList(){
    println("Input N")
    val number = readLine()!!

    println("Now input N numbers (space split): $number")
    val numbers = readLine()!!
    val list = numbers.split(" ").map{it.toInt()}
    println(list.sum())

//    var nums: List<Int> = emptyList()
//    println("Now input N numbers (enter split): $number")
//    for (i in 1..number.toInt()){
//        nums = nums + readLine()!!.toInt()
//    }
//    println(nums.sum())


}