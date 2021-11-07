//package com.example.calculator
//
//import android.arch.lifecycle.ViewModel
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.NonCancellable.cancel
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.runBlocking
//
//fun main(){
//    runBlocking {
//        // потоки согласно кол-ву ядер процессора
//        launch(Dispatchers.Default) {   printCurrentThread()
//        }
//        launch(Dispatchers.IO){
//            printCurrentThread()
//        }
//        // диспатчеры должны быть определены, в тестовом окружении мэйна нет
////        launch(Dispatchers.Main) {
////            printCurrentThread()
////        }
//        launch(Dispatchers.Unconfined) {  }
////        ViewModel()
//        Thread.sleep(4000)
//    }
//}
//
//private fun printCurrentThread(){
//    println(Thread.currentThread())
//}
//fun cancelAll(){
////    cancel()
//}
//
//// нужны для отмены наших задач в одном месте