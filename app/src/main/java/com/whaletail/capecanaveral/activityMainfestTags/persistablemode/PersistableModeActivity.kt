package com.whaletail.capecanaveral.activityMainfestTags.persistablemode

import android.os.BaseBundle
import android.os.Bundle
import android.os.PersistableBundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import com.whaletail.capecanaveral.R
import com.whaletail.capecanaveral.base.BaseActivity

import kotlinx.android.synthetic.main.activity_persistable_mode.*
import org.jetbrains.anko.info

class PersistableModeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_persistable_mode)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outPersistentState.putString("message", "O Hello There")
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        info { "onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?)" }
        info { persistentState?.getString("message") }
    }

}
