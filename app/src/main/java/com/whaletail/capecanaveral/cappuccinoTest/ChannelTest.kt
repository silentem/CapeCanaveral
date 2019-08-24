package com.whaletail.capecanaveral.cappuccinoTest

import com.whaletail.capecanaveral.log
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.selects.select
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class Scope(scope: CoroutineScope) : CoroutineScope by scope {


    val channel1: SendChannel<Pair<String, Channel<String>>> = actor {
        consumeEach {
            delay(1000)
            log("sleep for 1000")
            it.second.send("channel1 ${it.first}")
            it.second.close()
        }
    }

    val channel2: SendChannel<Pair<String, Channel<String>>> = actor {
        consumeEach {
            delay(1500)
            log("sleep for 1500")
            it.second.send("channel2 ${it.first}")
            it.second.close()
        }
    }

    suspend fun get(i: String) = select<String> {

        val channel = Channel<String>()

        val pair = i to channel
        channel1.onSend(pair) {
            channel.receive()
        }
        channel2.onSend(pair) {
            channel.receive()
        }



    }

}


fun main() = runBlocking {

    //    val scope = Scope(this)
//
//    val orders = produce {
//        for (i in listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)) {
//            send(i)
//        }
//    }
//
//
//    coroutineScope {
//        launch {
//            process(orders, scope)
//        }
//    }


    withTimeout(1000) {

    }

    val a1 = async {
        println("a1 before yield")
        yield()
        println("a1 after yield")
    }

    val a2 = async {
        println("a2 before yield")
        yield()
        println("a2 after yield")
    }

    a1.await()
    a2.await()

    return@runBlocking

}


private suspend fun process(i: ReceiveChannel<Int>, scope: Scope) {
    coroutineScope {
        i.map { o ->
            async { scope.get(o.toString()) }
        }.consumeEach {
            log(it.await())
        }
    }
}