package com.example.room

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.room.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDate

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val vm: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRv()
        setListeners()
        setObservers()
    }

    private fun setUpRv() {
        binding.notesRv.layoutManager = LinearLayoutManager(this)
        binding.notesRv.adapter = DateNoteAdapter {
            vm.onDelete(it)
        }
    }

    private fun setObservers() {

        vm.counter.observe(this) {
            binding.counterTv.text = it.toString()
        }

        vm.canCreate.observe(this) {
            binding.createBtn.isEnabled = it
        }

        vm.notes.observe(this) {
            (binding.notesRv.adapter as DateNoteAdapter).onNewDateNotes(it)
        }
    }

    private fun setListeners() {

        binding.deleteFab.setOnClickListener {
            vm.onDeleteAll()
        }

        binding.createBtn.setOnClickListener {
            vm.addNewNote()
        }

        val today = LocalDate.now()
        binding.calendarBtn.setOnClickListener {
            DatePickerDialog(this, { _, year, month, day ->
                val date = LocalDate.of(year, month + 1, day)
                vm.onDateSelected(date)
                binding.dateTv.text = "$day.$month.$year"
            }, today.year, today.monthValue - 1, today.dayOfMonth).show()
        }

        binding.titleEt.doAfterTextChanged {
            vm.onTitleEntered(it.toString())
        }

        binding.noteEt.doAfterTextChanged {
            vm.onNoteEntered(it.toString())
        }

        binding.typeRg.setOnCheckedChangeListener { group, checkId ->
            vm.onTypeSelected(group.indexOfChild(group.findViewById<RadioButton>(checkId)))
        }

    }
}