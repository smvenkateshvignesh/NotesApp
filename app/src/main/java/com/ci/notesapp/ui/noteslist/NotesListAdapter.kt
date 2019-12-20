package com.ci.notesapp.ui.noteslist

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.ci.notesapp.R
import com.ci.notesapp.ui.addnote.AddNoteActivity
import com.ci.notesapp.widgets.MyTextView

class NotesListAdapter(private val noteList:  ArrayList<NotesListModel>) : RecyclerView.Adapter<NotesListAdapter.ViewHolder>(){

    var notesClickListener: NotesClickListener?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_view, parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(noteList[position])

    }

    inner  class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {


        fun bind(noteList: NotesListModel){
            title.setText(noteList.title)
            description.setText(noteList.description)

            menuIcon.setOnClickListener{
                viewVisible(v = popUpCard)
                iconInvis(v = menuIcon)
            }

            closeIcon.setOnClickListener{
                inVisible(v = popUpCard)
                iconVis(v = menuIcon)
            }
            listCardMenu.setOnClickListener {

                notesClickListener?.onClick(noteList)
            }

        }

        fun viewVisible(v: View){
            popUpCard.visibility = View.VISIBLE
        }

        fun inVisible(v: View){
            popUpCard.visibility = View.INVISIBLE
        }
        fun iconInvis(v: View){
            menuIcon.visibility = View.INVISIBLE
        }
        fun iconVis(v: View){
            menuIcon.visibility = View.VISIBLE
        }

        val title: MyTextView
        val description: MyTextView
        val closeIcon: ImageView
        val editIcon: ImageView
        val deleteIcon: ImageView
        val menuIcon: ImageView
        val popUpCard: CardView
        val listCardMenu: CardView

        init {
            title = itemView.findViewById(R.id.titleTextView)
            description = itemView.findViewById(R.id.desTextview)
            closeIcon = itemView.findViewById(R.id.closeIcon)
            editIcon = itemView.findViewById(R.id.editIcon)
            deleteIcon = itemView.findViewById(R.id.deleteIcon)
            menuIcon = itemView.findViewById(R.id.menuImageIcon)
            popUpCard = itemView.findViewById(R.id.popUpCard)
            listCardMenu = itemView.findViewById(R.id.listCardMenu)

        }

    }
    fun setOnClickListener(notesClickListener: NotesClickListener){
        this.notesClickListener = notesClickListener
    }
    interface NotesClickListener{
        fun onClick(noteList: NotesListModel)
    }


}
