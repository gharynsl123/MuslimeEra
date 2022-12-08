package com.appteam.muslimeera.ui.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appteam.muslimeera.data.local.Notes
import com.appteam.muslimeera.databinding.GridItemNotesBinding
import com.appteam.muslimeera.until.HelperFunction

class NotesAdapter(
    val context: Context,
    val notesClickInterface: HelperFunction.NotesClickInterface,
    val notesClickDeleteInterface: HelperFunction.NotesClickDeleteInterface
) : RecyclerView.Adapter<NotesAdapter.MyNotesAdapter>(){

    private val allNotes = ArrayList<Notes>()

    fun updateList(newList : List<Notes>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }

    inner class MyNotesAdapter(val binding: GridItemNotesBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= MyNotesAdapter(
        GridItemNotesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MyNotesAdapter, position: Int) {
        val list = allNotes[position]
        holder.binding.apply {
            tvTitle.text = list.noteTitle
            tvDescription.text = list.noteDescription

            ivDelete.setOnClickListener {
                notesClickDeleteInterface.onDeleteIconClick(list)
            }
        }

        holder.itemView.setOnClickListener {
            notesClickInterface.onNoteClick(list)
        }
    }

    override fun getItemCount(): Int = allNotes.size
}