package com.whaletail.capecanaveral.customView

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.whaletail.capecanaveral.R
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.util.concurrent.TimeUnit

class CustomTestViewActivity : AppCompatActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_test_view)
        Observable.merge(
            Observable.fromArray(1, 2, 3, 4, 5)
                .delay(100, TimeUnit.MILLISECONDS),
            Observable.fromArray("1", "2", "3")
                .delay(150, TimeUnit.MILLISECONDS)
        ).subscribeBy(
                onNext = {
                    info { it }
                }
            )

    }
}
