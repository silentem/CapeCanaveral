package com.whaletail.capecanaveral.generallist

import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import com.whaletail.capecanaveral.R
import com.whaletail.capecanaveral.base.BaseActivity
import com.whaletail.capecanaveral.toPx
import kotlinx.android.synthetic.main.activity_general_list.*


class GeneralListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_general_list)
        packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
            .activities?.forEach {
            if (this@GeneralListActivity.javaClass.name != it.name) {
                root.addView(Button(this).apply {
                    text = it.name.replaceBeforeLast(".", "").replace("Activity", "").replace(".", "")
                    isAllCaps = false
                    layoutParams = ViewGroup.MarginLayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    ).apply {
                        setMargins(5.toPx, 5.toPx, 5.toPx, 5.toPx)
                    }
                    setOnClickListener { _ ->
                        startActivity(
                            Intent(
                                this@GeneralListActivity,
                                it.getActivityClass()
                            )
                        )
                    }
                })
            }
        }
    }


    fun ActivityInfo.getActivityClass(): Class<*> = Class.forName(name)

}
