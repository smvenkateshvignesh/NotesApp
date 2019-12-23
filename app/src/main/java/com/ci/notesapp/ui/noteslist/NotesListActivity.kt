package com.ci.notesapp.ui.noteslist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ci.notesapp.R
import com.ci.notesapp.base.BaseActivity
import com.ci.notesapp.ui.addnote.AddNoteActivity
import com.ci.notesapp.ui.database.DataBaseEntity
import com.ci.notesapp.ui.noteslist.adapter.NotesListAdapter
import kotlinx.android.synthetic.main.activity_notes_list.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class NotesListActivity : BaseActivity() {
    lateinit var notesListModel: ArrayList<NotesListModel>
    lateinit var myAdapter: NotesListAdapter
    val REQUEST_CODE = 1001

    companion object {
        const val BUNDLE_FROM = "from"
        const val BUNDLE_VALUE_NEW = "new"
        const val BUNDLE_VALUE_EDIT = "edit"
        const val BUNDEL_NOTES_DETAILS = "Notes Details"
    }

    override fun setLayout(): Int {
        return R.layout.activity_notes_list
    }

    override fun initView(savedInstanceState: Bundle?) {

        recyclerViewNotes.layoutManager =
            StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        myAdapter = NotesListAdapter()
        recyclerViewNotes.adapter = myAdapter
        myAdapter.notifyDataSetChanged()
        myAdapter.setOnClickListener(object : NotesListAdapter.NotesClickListener {
            override fun onNoteDelete(noteList: DataBaseEntity,itemPosition :Int) {
                doAsync {
                    getDAO().delete(noteList)
                    uiThread {
                        myAdapter.removeAt(itemPosition)

                    }
                }
            }

            override fun onClick(noteList: DataBaseEntity) {
                val myIntent = Intent(this@NotesListActivity, AddNoteActivity::class.java)
                myIntent.putExtra(BUNDLE_FROM, BUNDLE_VALUE_EDIT)
                myIntent.putExtra(BUNDEL_NOTES_DETAILS, noteList)
                startActivity(myIntent)
                myAdapter.notifyDataSetChanged()
            }
        })
        floatingActionButton.setOnClickListener {
            var intent = Intent(this@NotesListActivity, AddNoteActivity::class.java)
            intent.putExtra(BUNDLE_FROM, BUNDLE_VALUE_NEW)
            startActivityForResult(intent, REQUEST_CODE)
        }
        getNotesList()
    }


    private fun getNotesList() {
        doAsync {
            val list = getDAO().getNotesDetails()
            uiThread {
                myAdapter.updateList(list as ArrayList<DataBaseEntity>)
                myAdapter.notifyDataSetChanged()

            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK) {
                    getNotesList()
                }
            }
        }
    }
}
