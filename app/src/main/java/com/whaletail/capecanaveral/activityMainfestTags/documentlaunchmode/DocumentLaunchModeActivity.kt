package com.whaletail.capecanaveral.activityMainfestTags.documentlaunchmode

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import com.whaletail.capecanaveral.R
import com.whaletail.capecanaveral.base.BaseActivity

import kotlinx.android.synthetic.main.activity_document_launch_mode.*
import org.jetbrains.anko.info

class DocumentLaunchModeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_document_launch_mode)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        info { "new intent" }

    }

}
