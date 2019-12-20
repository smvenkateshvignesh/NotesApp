package com.ci.notesapp.ui.noteslist

import android.content.Intent
import android.icu.lang.UCharacter
import android.icu.text.CaseMap
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ci.notesapp.R
import com.ci.notesapp.base.BaseActivity
import com.ci.notesapp.ui.addnote.AddNoteActivity
import com.ci.notesapp.ui.database.DataBaseNotes
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_notes_list.*

class NotesListActivity : BaseActivity() {
    lateinit var notesListModel: ArrayList<NotesListModel>
    lateinit var myAdapter: NotesListAdapter

    companion object{
        const val BUNDEL_NOTES_DETAILS = "Notes Details"
    }

    override fun setLayout(): Int {

        return R.layout.activity_notes_list
    }
    override fun initView(savedInstanceState: Bundle?) {

        notesListModel = ArrayList()
        notesListModel.add(NotesListModel("My first Note", "9" + "I have this code that is supposed to make an image visible, but I don't know exactly how it's supposed to be written for Kotlin."))
        notesListModel.add(NotesListModel("My Second Note", "9" + "I have this code that is supposed to make an image visible, but I don't know exactly how it's supposed to be written for Kotlin" +
                "I have this code that is supposed to make an image visible, but I don't know exactly how it's supposed to be written for Kotlin" +
                "I have this code that is supposed to make an image visible, but I don't know exactly how it's supposed to be written for Kotlin."))
        notesListModel.add(NotesListModel("My Third Note", "9" + "I have this code that is supposed to make an image visible, but I don't know exactly how it's supposed to be written for Kotlin."))
        notesListModel.add(NotesListModel("My Fourth Note", "9" + "I have this code that is supposed to make an image visible, but I don't know exactly how it's supposed to be written for Kotlin."))
        notesListModel.add(NotesListModel("My Fifth Note", "9" +
                "I have this code that is supposed to make an image visible, but I don't know exactly how it's supposed to be written for Kotlin I have this code that is supposed to make an image visible, but I don't know exactly how it's supposed to be written for Kotlin."))
        notesListModel.add(NotesListModel("My Sixth Note", "9" + "I have this code that is supposed to make an image visible, but I don't know exactly how it's supposed to be written for Kotlin."))

        recyclerViewNotes.layoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
        myAdapter= NotesListAdapter(notesListModel)
        recyclerViewNotes.adapter=myAdapter



        myAdapter.setOnClickListener(object : NotesListAdapter.NotesClickListener{
            override fun onClick(noteList: NotesListModel) {
                val myIntent = Intent(this@NotesListActivity, AddNoteActivity::class.java)
                myIntent.putExtra(BUNDEL_NOTES_DETAILS, noteList)
                startActivity(myIntent)
            }
        })

        floatingActionButton.setOnClickListener{
            var intent = Intent(this@NotesListActivity, AddNoteActivity::class.java)
            startActivity(intent)
        }
    }
}
