package com.whaletail.capecanaveral.activityMainfestTags.relinquishactivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.whaletail.capecanaveral.R
import com.whaletail.capecanaveral.base.BaseActivity
import com.whaletail.capecanaveral.rx.RxCh1Activity
import kotlinx.android.synthetic.main.activity_relinquish_activity.*
import kotlinx.android.synthetic.main.content_relinquish_activity.*
import org.jetbrains.anko.info

class RelinquishActivityActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relinquish_activity)
        setSupportActionBar(toolbar)

        button.setOnClickListener {
            startActivity(Intent(this, RxCh1Activity::class.java).apply {
                putExtra("message", "Hello")

            })
        }

        fab.setOnClickListener { view ->

            info { intent?.getStringExtra("message") }

            if (Intent.ACTION_VIEW == intent.action) {
                val uri = intent.data

                Snackbar.make(view, uri.toString(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
        }
    }

}
