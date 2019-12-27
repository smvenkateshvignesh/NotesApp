package com.ci.notesapp.ui.noteslist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ci.notesapp.R
import com.ci.notesapp.ui.database.DataBaseEntity
import com.ci.notesapp.widgets.MyTextView

class NotesListAdapter : RecyclerView.Adapter<NotesListAdapter.ViewHolder>(), Filterable {
    private val noteList: ArrayList<DataBaseEntity> = ArrayList()
    private val searchList: ArrayList<DataBaseEntity> = ArrayList()
    var notesClickListener: NotesClickListener? = null
    var isMenuClicked:Boolean = true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.list_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.menuIcon.setOnClickListener {
           showMenuOption(holder.adapterPosition)
        }
        holder.closeIcon.setOnClickListener {
            noteList[holder.adapterPosition].isShowingEdit = false
            notifyItemChanged(holder.adapterPosition)
        }
        holder.bind(searchList[position])

    }

    private fun showMenuOption(adapterPosition: Int) {
        for((index,item) in noteList.withIndex()){
            if(index == adapterPosition){
                item.isShowingEdit = true
                notifyItemChanged(index)
            }else if(item.isShowingEdit){
                item.isShowingEdit = false
                notifyItemChanged(index)
            }

        }


    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(noteList: DataBaseEntity) {
            title.text = noteList.title
            description.text = noteList.description
            if(noteList.isShowingEdit){
                viewVisible()
            }else{
                inVisible()
            }

            deleteIcon.setOnClickListener {
                if (notesClickListener != null) {
                    notesClickListener?.onNoteDelete(noteList, adapterPosition)
                }
            }
            listCardMenu.setOnClickListener {
                notesClickListener?.onClick(noteList)
            }

            editIcon.setOnClickListener {
                notesClickListener?.onClick(noteList)

            }
        }

        private fun viewVisible() {
            popUpCard.visibility = View.VISIBLE
            menuIcon.visibility = View.INVISIBLE
        }

        private fun inVisible() {
            popUpCard.visibility = View.INVISIBLE
            menuIcon.visibility = View.VISIBLE
        }

        private val title: MyTextView = itemView.findViewById(R.id.titleTextView)
        private val description: MyTextView = itemView.findViewById(R.id.desTextview)
         val closeIcon: ImageView = itemView.findViewById(R.id.closeIcon)
        val editIcon: ImageView = itemView.findViewById(R.id.editIcon)
        private val deleteIcon: ImageView = itemView.findViewById(R.id.deleteIcon)
         val menuIcon: ImageView = itemView.findViewById(R.id.menuImageIcon)
        private val popUpCard: CardView = itemView.findViewById(R.id.popUpCard)
        private val listCardMenu: CardView = itemView.findViewById(R.id.listCardMenu)

    }

    fun setOnClickListener(notesClickListener: NotesClickListener) {
        this.notesClickListener = notesClickListener
    }

    interface NotesClickListener {
        fun onClick(noteList: DataBaseEntity)
        fun onNoteDelete(noteList: DataBaseEntity, itemPosition: Int)
    }

    /*
    *
    * Updating the file when ever we edit the note file....
    * */
    fun updateList(noteList: ArrayList<DataBaseEntity>) {
        clearList()
        this.searchList.addAll(noteList)
        this.noteList.addAll(noteList)
       notifyDataSetChanged()
    }
    /*
    *
    * this is for deleting the file from the database....
    * */
    fun removeAt(position: Int) {
        this.searchList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun clearList() {
        this.searchList.clear()
        this.noteList.clear()
        notifyDataSetChanged()
    }
    /*
    *--------- the code for filtering the list------
    *
    * */
    override fun getFilter(): Filter {
        return noteFilter
    }

    private val noteFilter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val noteFilterList: ArrayList<DataBaseEntity> = ArrayList()
            if (constraint?.isEmpty() == true) {
                noteFilterList.addAll(noteList)
            } else {
                val searchedName = constraint.toString().trim()
                for (i in noteList) {
                    if (i.title!!.contains(searchedName, true)) {
                        noteFilterList.add(i)
                    }
                }
            }
            val results = FilterResults()
            results.values = noteFilterList
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            searchList.clear()
            if (results?.values != null) {
                val searchedResults = results.values as ArrayList<DataBaseEntity>
                searchList.addAll(searchedResults)
            }
            notifyDataSetChanged()
        }
    }
}

