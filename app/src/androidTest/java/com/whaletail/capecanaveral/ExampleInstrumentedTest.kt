package com.whaletail.capecanaveral

import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import org.assertj.core.api.Assertions.assertThat

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertThat("com.whaletail.capecanaveral"== appContext.packageName)
    }
}
