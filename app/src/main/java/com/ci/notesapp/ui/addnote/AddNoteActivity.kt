package com.ci.notesapp.ui.addnote

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.ci.notesapp.R
import com.ci.notesapp.base.BaseActivity
import com.ci.notesapp.ui.database.DataBaseEntity
import com.ci.notesapp.ui.noteslist.NotesListActivity
import kotlinx.android.synthetic.main.activity_add_note.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class AddNoteActivity : BaseActivity() {
    private var update: Boolean = false
    private var oldData: DataBaseEntity? = null
    private var from = NotesListActivity.BUNDLE_VALUE_NEW

    override fun setLayout(): Int {
        return R.layout.activity_add_note
    }

    override fun initView(savedInstanceState: Bundle?) {
        getNoteDetails()
        listeners()
    }

    private fun listeners() {
        backImg.setOnClickListener {
            val myIntent = Intent()
            myIntent.putExtra(NotesListActivity.BUNDLE_FROM, 10001)
            setResult(Activity.RESULT_OK, myIntent)
            onBackPressed()
        }

        saveImg.setOnClickListener {
            when (from) {
                NotesListActivity.BUNDLE_VALUE_NEW -> {
                    insertNewNotes()
                }
                NotesListActivity.BUNDLE_VALUE_EDIT -> {
                    updateDetails()

                }
            }
        }
    }

    private fun updateDetails() {
        if (update) {
            oldData?.title =  myTitleEV?.text.toString()
            oldData?.description = myDesEV?.text.toString()
            doAsync {
                getDAO().updateMyNotes(oldData!!)
                uiThread {
                    setResult(Activity.RESULT_OK)
                    finish()
                }
            }

        } else {
            DataBaseEntity(myTitleEV!!.text.toString(), myDesEV!!.text.toString())
            insertNewNotes()
        }

    }

    private fun insertNewNotes() {
        val title = myTitleEV.text.toString()
        val desc = myDesEV.text.toString()
        if (title.isEmpty()) {
            showSnackBar("Please Enter Title")
        } else if (desc.isEmpty()) {
            showSnackBar("Please Enter Description")
        } else {

            doAsync {
                getDAO().insert(DataBaseEntity(title = title, description = desc))
                uiThread {
                    myDesEV.setText("")
                    myTitleEV.setText("")
                    showSnackBar("New Note Saved")
                }
            }
        }
    }

    private fun getNoteDetails() {
        if (intent.hasExtra(NotesListActivity.BUNDLE_FROM)) {
            from = intent.getStringExtra(NotesListActivity.BUNDLE_FROM)
        }

        if (intent.hasExtra(NotesListActivity.BUNDEL_NOTES_DETAILS)) {
            update = true
            oldData =
                intent.getSerializableExtra(NotesListActivity.BUNDEL_NOTES_DETAILS) as? DataBaseEntity
            myTitleEV.setText(oldData?.title)
            myDesEV.setText(oldData?.description)

        }
    }
}
