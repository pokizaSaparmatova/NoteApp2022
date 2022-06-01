package uz.gita.noteapp.presentation.viewmodel.impl

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.noteapp.data.model.NoteModel
import uz.gita.noteapp.data.source.local.room.entity.NoteEntity
import uz.gita.noteapp.domin.usecase.AddNoteUseCase
import uz.gita.noteapp.presentation.viewmodel.AddNoteScreenViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class AddNoteScreenViewModelImpl @Inject constructor(private val addNoteUseCase: AddNoteUseCase) :
    AddNoteScreenViewModel, ViewModel() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun addNote(note: NoteModel) {
        val currentTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS")
        val formatted = currentTime.format(formatter)

        addNoteUseCase.addNote(
            NoteEntity(
                note.title,
                note.content,
                formatted.substring(0, 10),
                dateTime = formatted,
                type = false,
                check = ""
            )
        )
            .onEach {
                it.onSuccess {
                }

            }.launchIn(viewModelScope)

    }

    override fun updateNote(title: String, content: String, date: String, id: String,type:Boolean) {
        addNoteUseCase.updateNote(title, content, date, id,type).onEach {
            it.onSuccess {

            }
        }.launchIn(viewModelScope)
    }

//    @RequiresApi(Build.VERSION_CODES.O)
//    override fun updateNote(title: String, content: String, date: String, id: String) {
//        val currentTime = LocalDateTime.now()
//        val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS")
//        val formatted = currentTime.format(formatter)
//        addNoteUseCase.updateNote(title,content,formatted.substring(0,10),id)
//            .onEach {
//                it.onSuccess { result->
//
//                }
//            }.launchIn(viewModelScope)
//    }
}