package com.whaletail.capecanaveral.workermanager

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.work.*
import com.whaletail.capecanaveral.R
import com.whaletail.capecanaveral.base.BaseActivity
import kotlinx.android.synthetic.main.activity_worker_manager.*
import kotlinx.coroutines.delay
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class WorkerManagerActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_worker_manager)
        val continuation = WorkManager.getInstance(applicationContext).also { workManager ->
            workManager.getWorkInfosByTagLiveData("tag-1")
                .observe(this, Observer { workInfo ->
                    info { "from live data workinfo.size ${workInfo.size}" }
                    if (workInfo.size > 0) {
                        info { workInfo[0].outputData.getString("tag-1") }
                    }
                })
            workManager.getWorkInfosByTagLiveData("tag-2")
                .observe(this, Observer { workInfo ->
                    info { "from live data workinfo.size ${workInfo.size}" }
                    if (workInfo.size > 0) {
                        info { workInfo[0].outputData.getString("tag-2") }
                    }
                })
        }.beginUniqueWork(
            "work-1",
            ExistingWorkPolicy.REPLACE,
            OneTimeWorkRequest.Builder(FirstWorker::class.java)
                .setInputMerger(ArrayCreatingInputMerger::class.java)
                .addTag("tag-1")
                .build()
        )
            .then(OneTimeWorkRequest.Builder(SecondWorker::class.java).addTag("tag-2").build())
            .then(OneTimeWorkRequest.Builder(ThirdWorker::class.java).addTag("tag-3").build())

        b_start_work.setOnClickListener {

            info { "launch ${Thread.currentThread().id}" }
            continuation.enqueue()
        }
    }

}


class FirstWorker(context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams), AnkoLogger {
    override suspend fun doWork(): Result {

        info { "FirstWorker before delay ${Thread.currentThread().id}" }
        delay(3000)
        info { "FirstWorker after delay" }


        val outputData = Data.Builder().putString(
            "tag-1", "FirstWorker"
        ).build()

        return Result.success(outputData)

    }


}

class SecondWorker(context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams), AnkoLogger {
    override suspend fun doWork(): Result {

        info { "SecondWorker before delay" }
        delay(3000)
        info { "SecondWorker after delay" }

        val outputData = Data.Builder().putString(
            "tag-2", "SecondWorker"
        ).build()

        return Result.success(outputData)

    }


}

class ThirdWorker(context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams), AnkoLogger {
    override suspend fun doWork(): Result {

        info { "ThirdWorker before delay" }
        delay(3000)
        info { "ThirdWorker after delay" }

        val outputData = Data.Builder().putString(
            "tag-3", "ThirdWorker"
        ).build()

        return Result.success(outputData)

    }


}
