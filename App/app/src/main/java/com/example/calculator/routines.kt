package com.example.calculator
//
//import kotlinx.coroutines.*
//import kotlinx.coroutines.channels.ticker
//import kotlin.system.measureTimeMillis
//
//// корутина - не поток. это сущность которая работает с каким-то тредом потоков
//// может приостановить свою деятельность
//// мы - программа, есть 2 задачи - вскипятить воду в чайнике и постирать вещи в машинке
//// идем к чайнику наливаем воду включаем чайник потом спим ждем
//// корутин позволяет запустить задачу и пойти делать другие
//
//fun main(){
////     запускает корутину и дожидается окончания
////
////    runBlocking {
////        repeat(100_000){
////            launch{
////                delay(1000)
////                println('.')
////            }
////
////        }
////
////        print("sup ")
////
////    }
////    println("test".double())
////    val action: String.() -> Unit ={
////        double()
////        length
////    }
////    "test".action()
//    val time = measureTimeMillis {
//        runBlocking{
//            launch{
//                timer(5000)
//            }
//        }
//
//    }
//    println("Time $time")
//}
//
//// extension функция
//// класс нельзя менять, но можно расширить
//
//fun String.double() = this + this
//
//// suspend - приостановить можем в какой-то момент и потом вернуться
//private suspend fun doWorld (){
//    coroutineScope{
//        val job: Job = launch{
//            delay(3000)
//            println("World 1")
//        }
//        job.cancel()
//        println(job.isCompleted)
//        launch {
//            delay(3000)
//
//            println("World 2")
//        }
//
//    }
//    println("Hello")
//}
//
//// легковесность
//// 100 тысяч потоков
//suspend fun computation1(): Int{
//    delay(2000L)
//    return 2
//}
//
////private suspend fun makeComputation (): Int = coroutineScope{
////    var result1: DeferredInt<Int> = async{ computation1()}
////
////}
//
//// первые две секунды печатать время каждые 0.1 секунды
//// до 10 секунд принтить каждую секунду
//// далее каждые 2 секунды
//// @param time = время работы таймера
//private suspend fun timer(time: Long){
//    var delayedTime = 0
//    while(delayedTime < time){
//        val deltaDelay = when{
//            delayedTime < 2_000 -> 100
//            delayedTime < 10_000 -> 200
//            else -> 2000
//        }
//
//        val delta = delay(deltaDelay.toLong())
//        delayedTime += deltaDelay
//        println(delayedTime)
//
//    }
//
//}
//
