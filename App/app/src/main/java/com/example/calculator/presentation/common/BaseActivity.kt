package com.example.calculator.presentation.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
//import androidx.appcompat.app.AppCompatActivity
import android.util.Log

abstract class BaseActivity : AppCompatActivity() {

    private val tag: String = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag, "onCreate")
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    abstract fun <T> viewModels(t: T): T
}