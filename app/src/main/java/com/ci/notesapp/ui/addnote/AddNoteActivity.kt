package com.ci.notesapp.ui.addnote

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import com.ci.notesapp.R
import com.ci.notesapp.base.BaseActivity
import com.ci.notesapp.ui.noteslist.NotesListActivity
import com.ci.notesapp.ui.noteslist.NotesListModel
import kotlinx.android.synthetic.main.activity_add_note.*

class AddNoteActivity: BaseActivity() {

    override fun setLayout(): Int {
        return  R.layout.activity_add_note
    }

    override fun initView(savedInstanceState: Bundle?) {

        getNoteDetails()

        backImg.setOnClickListener{
            var intent = Intent(this@AddNoteActivity, NotesListActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun getNoteDetails(){

        if (intent.hasExtra(NotesListActivity.BUNDEL_NOTES_DETAILS)) {

            val addNote: NotesListModel? = intent.getSerializableExtra(NotesListActivity.BUNDEL_NOTES_DETAILS) as? NotesListModel
            myTitleEV.setText(addNote?.title)
            myDesEV.setText(addNote?.description)

        }
    }
}
