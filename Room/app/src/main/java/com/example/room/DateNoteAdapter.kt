package com.example.room

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.room.databinding.ItemDateNoteBinding
import com.example.room.model.DateNote
import com.example.room.model.NoteType

class DateNoteAdapter(
    val onDelete: (Int) -> Unit
) : RecyclerView.Adapter<DateNoteAdapter.VH>() {
    var dateNotes = mutableListOf<DateNote>()

    fun onNewDateNotes(new: List<DateNote>) {
        dateNotes = new.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = ItemDateNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(view)
    }

    override fun getItemCount() = dateNotes.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val note = dateNotes[position]
        with(holder.binding){
            titleTv.text = note.title
            notesTv.text = note.note
            dateTv.text = note.date.toString()
            typeIv.setImageResource(getTypeId(note.type))
//todo fix            root.background = getItemColor(note.type)
            root.setOnClickListener {
                onDelete(note.id)
            }
        }
    }

    private fun getTypeId(type: NoteType?): Int {
        return when(type){
            NoteType.Birthday -> R.drawable.ic_birthday
            NoteType.Business -> R.drawable.ic_business
            else -> R.drawable.ic_other
        }
    }

    private fun getItemColor(type: NoteType?): String {
        return when(type){
            NoteType.Birthday -> "#88FF00FF"
            NoteType.Business ->  "#8800FF00"
            else -> "#880000FF"
        }
    }

    inner class VH(
        val binding: ItemDateNoteBinding
    ) : RecyclerView.ViewHolder(binding.root)

}