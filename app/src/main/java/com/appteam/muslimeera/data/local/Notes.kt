package com.appteam.muslimeera.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Make The Database Room
@Entity(tableName = "noteTable")
class Notes(
    @ColumnInfo(name = "title") val noteTitle: String,
    @ColumnInfo(name = "description") val noteDescription: String,
    @ColumnInfo(name = "timestap") val noteTime: String
) {
    @PrimaryKey(autoGenerate = true) var id = 0
}