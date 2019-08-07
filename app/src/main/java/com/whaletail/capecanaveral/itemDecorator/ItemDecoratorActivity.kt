package com.whaletail.capecanaveral.itemDecorator

import android.os.Bundle
import com.whaletail.capecanaveral.R
import com.whaletail.capecanaveral.base.BaseActivity
import kotlinx.android.synthetic.main.activity_item_decorator.*

class ItemDecoratorActivity : BaseActivity() {

    lateinit var adapter: ItemDecoratorAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_decorator)

        adapter = ItemDecoratorAdapter()

        recyclerView.adapter = adapter

        recyclerView.addItemDecoration(ItemDecoratorDecorator(this)?.apply {
            setDays(listOf(1, 2, 3, 4, 5, 6, 7, 8))
        })

    }
}
