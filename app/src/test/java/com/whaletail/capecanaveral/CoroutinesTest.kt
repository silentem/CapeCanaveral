package com.whaletail.capecanaveral

import androidx.appcompat.app.AppCompatActivity
import dalvik.annotation.TestTarget
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import org.junit.Test
import kotlin.coroutines.CoroutineContext

class CoroutinesTest : CoroutineScope by CoroutineScope(Dispatchers.Default){


    @Test
    fun testCappuccinoChannels() {

    }

    @Test
    fun testDispatchersAndThreads() = runBlocking<Unit> {
        launch {
            println("1 main runBlocking      : I'm working in thread ${Thread.currentThread().name}")
        }
        launch(Dispatchers.Unconfined) {
            println("2 main runBlocking      : I'm working in thread ${Thread.currentThread().name}")
        }
        launch(Dispatchers.IO) {
            println("3 main runBlocking      : I'm working in thread ${Thread.currentThread().name}")
        }
        launch(newSingleThreadContext("NewSingleThreadContext")) {
            println("4 main runBlocking      : I'm working in thread ${Thread.currentThread().name}")
        }
    }

    @Test
    fun testUnconfinedVsConfinedDispatcher() = runBlocking<Unit> {
        launch(Dispatchers.Unconfined) {
            println("1 unconfined before      : I'm working in thread ${Thread.currentThread().name}")
            delay(500)
            println("1 unconfined after      : I'm working in thread ${Thread.currentThread().name}")
        }
        launch {
            println("1 main before      : I'm working in thread ${Thread.currentThread().name}")
            delay(1000)
            println("1 main after      : I'm working in thread ${Thread.currentThread().name}")
        }
    }

    @Test
    fun test_coroutineScope_creation() {
        CoroutineScope(Dispatchers.IO).launch(Dispatchers.Main) {

        }
    }

    @Test
    fun testProduce() {
        runBlocking {
            val receiveChannel = produce {
                repeat(10) {
                    send(it.toString())
                }
            }

            receiveChannel.consumeEach { println(it) }

        }
    }

    @Test
    fun testJoin() {
        runBlocking {
            val currentTimeMillis = System.currentTimeMillis()
            val job = launch {
                delay(1000)
                println("After parallel delay")
            }
            job.join()
            println("${System.currentTimeMillis() - currentTimeMillis}")
        }
    }

    @Test
    fun testCoroutines() {
        runBlocking {
            val currentTimeMillis = System.currentTimeMillis()
            val first = async { firstNumber() }
            val second = async { secondNumber() }
            val third = async { thirdNumber() }
            val result = first.await() + second.await() + third.await()
            println("$result")
            println("${System.currentTimeMillis() - currentTimeMillis}")
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