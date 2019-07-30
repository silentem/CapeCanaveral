package com.whaletail.capecanaveral.activityMainfestTags.launchmode

import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.whaletail.capecanaveral.R
import com.whaletail.capecanaveral.activityMainfestTags.parentactivityname.ParentActivityNameActivity
import com.whaletail.capecanaveral.base.BaseActivity
import kotlinx.android.synthetic.main.activity_launch_mode_standart.*
import kotlinx.android.synthetic.main.content_launch_mode_standart.*
import org.jetbrains.anko.info
import android.app.*
import androidx.core.app.TaskStackBuilder
import com.whaletail.capecanaveral.activityMainfestTags.documentlaunchmode.DocumentLaunchModeActivity


class LaunchModeStandartActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_mode_standart)
        setSupportActionBar(toolbar)

        info { "From onCreate ${intent?.getStringExtra("message")}" }

        b_reparenting.setOnClickListener {

            val intent = Intent(this, ParentActivityNameActivity::class.java)

            TaskStackBuilder.create(this)
                .addNextIntentWithParentStack(intent)
                .startActivities()
        }

        fab.setOnClickListener { view ->
            startActivity(Intent(this, LaunchModeStandartActivity::class.java).apply {
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
