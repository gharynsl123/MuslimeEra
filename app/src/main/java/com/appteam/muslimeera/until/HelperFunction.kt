package com.appteam.muslimeera.until

import com.appteam.muslimeera.data.local.Notes

object HelperFunction {
    interface NotesClickDeleteInterface {
        fun onDeleteIconClick(note: Notes)
    }

    interface NotesClickInterface {
        fun onNoteClick(note: Notes)
    }


}