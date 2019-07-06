package com.whaletail.capecanaveral.count

import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.whaletail.capecanaveral.base.BaseService
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.PublishSubject
import org.jetbrains.anko.info
import java.util.concurrent.TimeUnit


class ObservableService : BaseService() {

    var data = PublishSubject.create<String>()

    inner class ObservableBinder : Binder() {
        fun getDataObservable() = data

        var disposable: Disposable? = null

        fun startCounting() {
            info { "Start counting" }
            disposable?.dispose()
            disposable = Observable
                .interval(1, TimeUnit.SECONDS)
                .doOnNext {
                    info { "doOnNext $it ${Thread.currentThread().name}" }
                    data.onNext(it.toString())
                }
                .subscribeBy(
                    onError = { it.printStackTrace() }
                )
        }

        fun stopCounting() {
            info { "Stop counting" }
            disposable?.dispose()
        }

    }

    override fun onBind(intent: Intent): IBinder {
        return ObservableBinder()
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

}
