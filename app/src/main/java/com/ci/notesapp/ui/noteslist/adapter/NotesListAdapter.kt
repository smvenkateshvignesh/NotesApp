package com.ci.notesapp.ui.noteslist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ci.notesapp.R
import com.ci.notesapp.ui.database.DataBaseEntity
import com.ci.notesapp.widgets.MyTextView

class NotesListAdapter : RecyclerView.Adapter<NotesListAdapter.ViewHolder>() {
    private val noteList: ArrayList<DataBaseEntity> = ArrayList()
    var notesClickListener: NotesClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.list_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(noteList[position])

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(noteList: DataBaseEntity) {
            title.text = noteList.title
            description.text = noteList.description

            menuIcon.setOnClickListener {
                viewVisible(v = popUpCard)
                iconInvis(v = menuIcon)
            }

            closeIcon.setOnClickListener {
                inVisible(v = popUpCard)
                iconVis(v = menuIcon)
            }
            deleteIcon.setOnClickListener {
                if (notesClickListener != null) {
                    notesClickListener?.onNoteDelete(noteList,adapterPosition)
                }

            }

            listCardMenu.setOnClickListener {
                notesClickListener?.onClick(noteList)
            }
        }

        private fun viewVisible(v: View) {
            popUpCard.visibility = View.VISIBLE
        }

        private fun inVisible(v: View) {
            popUpCard.visibility = View.INVISIBLE
        }

        private fun iconInvis(v: View) {
            menuIcon.visibility = View.INVISIBLE
        }

        private fun iconVis(v: View) {
            menuIcon.visibility = View.VISIBLE
        }

        private val title: MyTextView = itemView.findViewById(R.id.titleTextView)
        private val description: MyTextView = itemView.findViewById(R.id.desTextview)
        private val closeIcon: ImageView = itemView.findViewById(R.id.closeIcon)
        val editIcon: ImageView = itemView.findViewById(R.id.editIcon)
        private val deleteIcon: ImageView = itemView.findViewById(R.id.deleteIcon)
        private val menuIcon: ImageView = itemView.findViewById(R.id.menuImageIcon)
        private val popUpCard: CardView = itemView.findViewById(R.id.popUpCard)
        private val listCardMenu: CardView = itemView.findViewById(R.id.listCardMenu)

    }

    fun setOnClickListener(notesClickListener: NotesClickListener) {
        this.notesClickListener = notesClickListener
    }

    interface NotesClickListener {
        fun onClick(noteList: DataBaseEntity)
        fun onNoteDelete(noteList: DataBaseEntity,itemPosition :Int)
    }

    fun updateList(noteList: ArrayList<DataBaseEntity>) {
        clearList()
        val startingPosition = this.noteList.size
        this.noteList.addAll(noteList)
        notifyItemRangeInserted(startingPosition, noteList.size)
    }
    fun removeAt(position:Int) {
        this.noteList.removeAt(position)
        notifyItemRemoved(position)
    }
    fun clearList() {
        this.noteList.clear()
        notifyDataSetChanged()
    }
}
