package com.example.room.di

//room
import androidx.room.Room
import com.example.room.DateNotesRepository
import com.example.room.MainViewModel
import com.example.room.dao.DateNoteTakerDb

//koin
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val mainModule = module {
    viewModelOf(::MainViewModel)
    singleOf(::DateNotesRepository)
}

val daoModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            DateNoteTakerDb::class.java,
            "date-note-taker-db")
            .build() }

    single { get<DateNoteTakerDb>().dateNoteDao() }
}

val appModules = listOf(mainModule, daoModule)

