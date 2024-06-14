package com.example.room

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.room.model.DateNote
import com.example.room.model.NoteType
import kotlinx.coroutines.launch
import java.time.LocalDate

class MainViewModel(
    private val repo: DateNotesRepository
) : ViewModel() {

    val canCreate = MutableLiveData(false)
    val notes = MutableLiveData<List<DateNote>>(listOf())
    val counter = MutableLiveData(0)

//    init {
//        viewModelScope.launch {
//            notes.postValue(repo.getNotes())
//        }
//
//    }

    private var newDateNote = DateNote()

    fun onDateSelected(date: LocalDate) {
        newDateNote = newDateNote.copy(date = date)
        checkIfValid()
    }

    fun onTitleEntered(title: String) {
        newDateNote = newDateNote.copy(title = title)
        checkIfValid()
    }

    fun onNoteEntered(note: String) {
        newDateNote = newDateNote.copy(note = note)
        checkIfValid()
    }

    fun onTypeSelected(index: Int) {
        newDateNote = newDateNote.copy(type = NoteType.entries[index])
        checkIfValid()
    }

    private fun checkIfValid() {
        val isValid = newDateNote.date != null
                && newDateNote.title != null
                && newDateNote.type != null
        canCreate.value = isValid
    }

    fun addNewNote() {
        //todo fix
        viewModelScope.launch {
            repo.insertNote(newDateNote)
            updateNotes()
        }
    }

    fun onDelete(id: Int) {
        viewModelScope.launch {
            repo.deleteById(id)
            updateNotes()
        }
    }

    fun onDeleteAll() {
        viewModelScope.launch {
            repo.deleteAll()
            updateNotes()
        }
    }

    private fun onCountAll() {
        viewModelScope.launch {
            counter.postValue(repo.countAll())
        }
    }

    private fun updateNotes() = viewModelScope.launch {
        notes.postValue(repo.getNotes())
        onCountAll()
    }

}