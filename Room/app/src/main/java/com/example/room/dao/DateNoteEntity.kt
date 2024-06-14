package com.example.room.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DateNoteEntity (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val note: String,
    val date: String,
    val noteType: String
)

// must repeat same structure:
//data class DateNote(
//    val id: Int = -1,
//    val title: String? = null,
//    val note: String? = null,
//    val date: LocalDate? = null,
//    val type: NoteType? = null
//)