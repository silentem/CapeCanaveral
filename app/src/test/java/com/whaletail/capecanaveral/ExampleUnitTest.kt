package com.whaletail.capecanaveral

import io.reactivex.Observable
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.util.concurrent.TimeUnit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    val messageCorrect = "messageCorrect"
    val messageWrong = "messageWrong"
    val observableError = Observable.error<String>(RuntimeException(messageCorrect))
        .flatMap(
            { s -> Observable.empty<String>() },
            { t ->
                Observable.error<String>(t)
            },
            { Observable.empty<String>() }
        )

    val observableComplete = Observable.empty<String>()
        .flatMap(
            { s -> Observable.empty<String>() },
            { t ->
                Observable.error<String>(t)
            },
            { Observable.just(messageCorrect) }
        )

    val observableStartWithDelay = Observable.just(1, 2, 3, 4, 5)
        .delay(5, TimeUnit.SECONDS)
        .startWith(0)
        .timestamp()
        .map { it.time() }
        .collectInto(mutableListOf<Long>()) { list, item -> list.add(item) }

    @Test
    fun flatMap_onError_wrong() {
        observableError
            .test()
            .assertError {
                check {
                    assertThat(it)
                        .isNotEqualTo(messageWrong)
                }
            }
            .assertNoValues()
            .assertNotComplete()
    }

    @Test
    fun flatMap_onError_correct() {
        observableError
            .test()
            .assertError { it.localizedMessage == messageCorrect }
            .assertNoValues()
            .assertNotComplete()
    }

    @Test
    fun flatMap_onComplete_correct() {
        observableComplete
            .test()
            .assertValue(messageCorrect)
            .assertComplete()
            .assertNoErrors()
    }

    @Test
    fun flatMap_startWith_delay() {

        val testObserver = observableStartWithDelay
            .test()
        testObserver
            .awaitTerminalEvent()

        testObserver.assertValue {
            check {
                assertThat(it.filterWithNextItem { current, next -> current - next >= 5000 })
                    .isNotEmpty()
            }
        }.assertValue {
            check {
                assertThat(it.size).isEqualTo(6)
            }
        }

    }

    private fun <T> List<T>.filterWithNextItem(predicate: (current: T, next: T) -> Boolean): List<T> =
        filterIndexed { index, l ->
            if (this.size != index + 1) {
                predicate(this[index + 1], l)
            } else {
                false
            }
        }

    fun check(consumer: () -> Unit): Boolean {
        consumer()
        return true
    }


}
