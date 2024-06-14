package com.example.room

import com.example.room.dao.DateNoteDao
import com.example.room.dao.DateNoteEntity
import com.example.room.model.DateNote
import com.example.room.model.NoteType
import java.time.LocalDate

class DateNotesRepository(
    val dao: DateNoteDao
) {

    // converted DateNote > DateNoteEntity
    suspend fun insertNote(new: DateNote): Long {
        return dao.insert(
            DateNoteEntity(
                title = new.title!!,
                note = new.note ?: "",
                noteType = new.type!!.name,
                date = new.date.toString()
            )
        )
    }

    suspend fun getNotes(): List<DateNote> {
        return dao.getAll().map {
            DateNote(
                title = it.title,
                note = it.note,
                id = it.id,
                date = LocalDate.parse(it.date),
                type = NoteType.valueOf(it.noteType)
            )
        }
    }

    suspend fun deleteById(id: Int) {
        dao.delete(id)
    }

    suspend fun deleteAll() {
        dao.deleteAll()
    }

    suspend fun countAll() : Int {
        return dao.getCount()
    }
}