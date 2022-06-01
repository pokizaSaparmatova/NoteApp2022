package uz.gita.noteapp.presentation.viewmodel.impl

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.noteapp.data.model.NoteModel
import uz.gita.noteapp.data.source.local.room.entity.NoteEntity
import uz.gita.noteapp.domin.usecase.AddNoteUseCase
import uz.gita.noteapp.presentation.viewmodel.AddCheckScreenViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class AddCheckScreenViewModelImpl @Inject constructor(
    private val checkUseCase: AddNoteUseCase
) : AddCheckScreenViewModel, ViewModel() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun addCheck(check: NoteModel) {
        val currentTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS")
        val formatted = currentTime.format(formatter)

        checkUseCase.addNote(
            NoteEntity(
                check.title,
                check.content,
                formatted.substring(0, 10),
                dateTime = formatted,
                type =true,
                check = ""
            )
        )
            .onEach {
                it.onSuccess {
                }

            }.launchIn(viewModelScope)
    }

    override fun updateCheck(title: String, content: String, date: String, id: String,type:Boolean) {
        checkUseCase.updateNote(title, content, date, id,type).onEach {
            it.onSuccess {

            }
        }.launchIn(viewModelScope)
    }

}