package com.example.room.model

import java.time.LocalDate

data class DateNote(
    val id: Int = -1,
    val title: String? = null,
    val note: String? = null,
    val date: LocalDate? = null,
    val type: NoteType? = null
)

enum class NoteType {
    Birthday, Business, Other
}
