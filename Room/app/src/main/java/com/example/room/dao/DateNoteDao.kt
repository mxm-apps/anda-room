package com.example.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface DateNoteDao {

    @Query("SELECT * FROM DateNoteEntity")
    suspend fun getAll(): List<DateNoteEntity>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: DateNoteEntity): Long

    @Query("DELETE FROM DateNoteEntity WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("DELETE FROM DateNoteEntity")
    suspend fun deleteAll()

    @Query("SELECT COUNT(*) FROM DateNoteEntity")
    suspend fun getCount(): Int

}
