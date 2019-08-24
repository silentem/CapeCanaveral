package com.whaletail.capecanaveral.cappuccinoTest

fun main() {
    select {
        print("Call 1")
        print("Call 2")
        print("Call 3")
    }
}

interface Interface {
    fun call()
}

class Class : Interface {
    override fun call() {
        print(1)
    }

}

public inline fun select(crossinline builder: Interface.() -> Unit) {
    val scope = Class()
    builder(scope)
}