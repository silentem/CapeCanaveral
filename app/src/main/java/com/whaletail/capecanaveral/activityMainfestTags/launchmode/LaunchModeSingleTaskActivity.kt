package com.whaletail.capecanaveral.activityMainfestTags.launchmode

import android.app.ActivityManager
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import com.whaletail.capecanaveral.R
import com.whaletail.capecanaveral.base.BaseActivity

import kotlinx.android.synthetic.main.activity_launch_mode_single_task.*
import org.jetbrains.anko.info

class LaunchModeSingleTaskActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_mode_single_task)
        setSupportActionBar(toolbar)

        info { "From onCreate ${intent?.getStringExtra("message")}" }

        fab.setOnClickListener { view ->
            info { "Task id: $taskId" }
            getActivityBackStack()
            startActivity(Intent(this, LaunchModeSingleTaskActivity::class.java).apply {
                putExtra("message", "message")
            })
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        info { "From onNewIntent ${intent?.getStringExtra("message")}" }
    }

    fun getActivityBackStack() {
        val m = getSystemService(ACTIVITY_SERVICE) as ActivityManager
        val runningTaskInfoList = m.getRunningTasks(10)
        info { "Task size: ${runningTaskInfoList.size}" }
        val itr = runningTaskInfoList.iterator()
        while (itr.hasNext()) {
            val runningTaskInfo = itr.next() as ActivityManager.RunningTaskInfo
            val id = runningTaskInfo.id
            val desc = runningTaskInfo.description
            info { "id: $id| desc: $desc" }
        }
    }

}
