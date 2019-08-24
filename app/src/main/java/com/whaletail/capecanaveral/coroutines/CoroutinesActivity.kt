package com.whaletail.capecanaveral.coroutines

import android.os.Bundle
import android.util.Log
import com.whaletail.capecanaveral.R
import com.whaletail.capecanaveral.base.BaseActivity
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CoroutinesActivity : BaseActivity(), CoroutineScope {


    //    val proxyHandler  = CoroutineExceptionHandler { coroutineContext, throwable ->
//        CoroutineExceptionHandler { coroutineContext, throwable ->
//            Log.d("TAG", throwable.message)
//        }
//    }
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)

        testCoroutines()

    }


    fun testCoroutines() {
        launch {
            val currentTimeMillis = System.currentTimeMillis()
            val first = async { firstNumber() }
            val second = async { secondNumber() }
            val third = async { thirdNumber() }
            val result = first.await() + second.await() + third.await()
            Log.d("TAG", "$result")
            Log.d("TAG", "${System.currentTimeMillis() - currentTimeMillis}")
        }
    }


    suspend fun firstNumber(): Int {
        delay(3_000) // 3 seconds delay
        return 5
    }

    suspend fun secondNumber(): Int {
        delay(5_000) // 5 seconds delay
        return 8
    }

    suspend fun thirdNumber(): Int {
        delay(7_000) // 7 seconds delay
        return 10
    }

}
