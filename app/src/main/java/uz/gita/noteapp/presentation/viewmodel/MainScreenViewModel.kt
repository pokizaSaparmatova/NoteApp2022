package uz.gita.noteapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.noteapp.data.source.local.room.entity.NoteEntity

interface MainScreenViewModel {
    val notesLiveData:LiveData<List<NoteEntity>>
    val queryNotesLiveData:LiveData<List<NoteEntity>>
    val errorLiveData:LiveData<Unit>
    val typeLiveData:LiveData<Boolean>

    fun getNotes()
    fun load()
    fun getNotesByQuery(query:String)
    fun deleteNote(id:String)
}