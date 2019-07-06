package com.whaletail.capecanaveral.count

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import com.whaletail.capecanaveral.R
import com.whaletail.capecanaveral.base.BaseActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_count.*
import org.jetbrains.anko.info


class CountActivity : BaseActivity() {

    val observableServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {

        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            service as ObservableService.ObservableBinder
            service.getDataObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {
                        tv_counter.text = it.toString()
                        info { it }
                    },
                    onError = { it.printStackTrace() }
                )

            b_start_count.setOnClickListener {
                service.startCounting()
            }
            b_stop_count.setOnClickListener {
                service.stopCounting()
            }

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count)

        bindService(
            Intent(this, ObservableService::class.java),
            observableServiceConnection,
            Context.BIND_AUTO_CREATE
        )

    }
}
