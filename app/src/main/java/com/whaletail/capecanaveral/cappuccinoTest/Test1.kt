package com.whaletail.capecanaveral.cappuccinoTest

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


fun main() {

}

suspend fun a(int: Int) {
    b(int)
    c(int*2)
}

suspend fun b(int: Int) {
    coroutineScope {
        println(async { 1 }.await())
    }
    print(int)
}

suspend fun c(int: Int) {
    print(int)
}