package com.whaletail.capecanaveral.channelExample

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.selects.select
import kotlinx.coroutines.sync.Mutex


fun main() = runBlocking {
    coroutineScope {
        val references = Channel<String>()
        processReferences(references)
        references.send("asdf")
        references.send("qwer")
        references.send("zxcv")
    }
}

fun CoroutineScope.processReferences(references: ReceiveChannel<String>) {
    val locations = Channel<String>()
    val contents = Channel<String>(1)
    worker(locations, contents)
    downloader(references, locations, contents)
}

fun CoroutineScope.downloader(
    references: ReceiveChannel<String>,
    locations: SendChannel<String>,
    contents: ReceiveChannel<String>) = launch {
    val requested = mutableSetOf<String>()
    while (isActive) {
        println("while")
        select<Unit> {
            println("select")
            contents.onReceive {
                requested.remove(it)
                println("[downloader-contents] $it")
            }
            references.onReceive {
                println("[downloader-references] $it")
                if (requested.add(it)) {
                    locations.send(it)
                }
            }
            println("-select")
        }
        println("-while")
    }

}

fun CoroutineScope.worker(
    locations: ReceiveChannel<String>,
    contents: SendChannel<String>) = launch {
    for (location in locations) {
        delay(2000)
//        println("[worker] $location")
        contents.send("content from $location")
    }
}
