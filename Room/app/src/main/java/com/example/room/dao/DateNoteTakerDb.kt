package com.example.room.dao

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DateNoteEntity::class], version = 1)
abstract class DateNoteTakerDb:RoomDatabase() {
   abstract fun dateNoteDao(): DateNoteDao
}