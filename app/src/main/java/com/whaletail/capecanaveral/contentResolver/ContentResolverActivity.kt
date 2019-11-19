package com.whaletail.capecanaveral.contentResolver

import android.content.ContentValues
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.UserDictionary
import com.whaletail.capecanaveral.R
import com.whaletail.capecanaveral.base.BaseActivity
import kotlinx.android.synthetic.main.activity_content_resolver.*
import org.jetbrains.anko.info


class ContentResolverActivity : BaseActivity() {


    private val projection: Array<String> = arrayOf(
        UserDictionary.Words._ID,
        UserDictionary.Words.WORD,
        UserDictionary.Words.LOCALE
    )

    private var selectionClause: String? = null

    private lateinit var selectionArgs: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_resolver)

        selectionArgs = "bukkak".takeIf { it.isNotEmpty() }?.let {
            selectionClause = "${UserDictionary.Words.WORD} = ?"
            arrayOf(it)
        } ?: run {
            selectionClause = null
            emptyArray<String>()
        }


        b_print.setOnClickListener {

            val cursor = contentResolver.query(
                ContactsContract.Contacts.CONTENT_URI,
                null,
                null,
                null,
                null
            )
            info { "count = ${cursor?.count}" }
            cursor?.apply {
                val index: Int = getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)

                while (moveToNext()) {
                    val word = getString(index)
                    info { word }
                }



                close()
            }
        }

        b_insert.setOnClickListener {


            val newValues = ContentValues().apply {

                val contentValues = ContentValues();

                contentValues.put(
                    ContactsContract.Data.MIMETYPE,
                    ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE
                )

                contentValues.put(ContactsContract.CommonDataKinds.Phone.NUMBER, "+380505748156")

                var phoneContactType = ContactsContract.CommonDataKinds.Phone.TYPE_HOME

                contentValues.put(ContactsContract.CommonDataKinds.Phone.TYPE, phoneContactType)

            }

            info {
                contentResolver.insert(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    newValues
                )
            }
        }

    }


}
