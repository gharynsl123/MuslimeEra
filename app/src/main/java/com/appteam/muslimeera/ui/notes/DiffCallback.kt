package com.appteam.muslimeera.ui.notes

import androidx.recyclerview.widget.DiffUtil
import com.appteam.muslimeera.data.local.Notes

class DiffCallback(private val oldList: List<Notes>, private val newList: List<Notes>) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldP: Int, newP: Int): Boolean {
        return oldList[oldP] == newList[newP]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldData = oldList[oldItemPosition]
        val newData = newList[newItemPosition]
        return oldData.id == newData.id &&
                oldData.noteTitle == newData.noteTitle &&
                oldData.noteDescription == newData.noteDescription
    }
}