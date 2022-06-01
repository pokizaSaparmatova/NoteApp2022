package uz.gita.noteapp.presentation.viewmodel.impl

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.noteapp.data.source.local.room.entity.NoteEntity
import uz.gita.noteapp.domin.usecase.impl.MainScreenUseCaseImpl
import uz.gita.noteapp.presentation.viewmodel.MainScreenViewModel
import javax.inject.Inject
@HiltViewModel
class MainScreenViewModelImpl @Inject constructor(private val mainScreenUseCase: MainScreenUseCaseImpl): MainScreenViewModel, ViewModel() {
    override val notesLiveData= MutableLiveData<List<NoteEntity>>()
    override val queryNotesLiveData= MutableLiveData<List<NoteEntity>>()
    override val errorLiveData= MutableLiveData<Unit>()
    override val typeLiveData=MutableLiveData<Boolean>()


    init {
        getNotes()
    }

    override fun getNotes() {
        mainScreenUseCase.getNotes().onEach {
            it.onSuccess {data->
                Log.d("QQQ", "$data")
                notesLiveData.value=data
            }.onFailure {
                errorLiveData.value=Unit
            }
        }.launchIn(viewModelScope)
    }

    override fun load() {
        getNotes()
    }

    override fun getNotesByQuery(query: String) {
        mainScreenUseCase.getNotesByQuery(query).onEach {data->
            data.onSuccess {
                notesLiveData.value=it
                Log.d("PPP", "$it")
            }.onFailure {
                errorLiveData.value=Unit
            }
        }.launchIn(viewModelScope)
    }

    override fun deleteNote(id: String) {
        mainScreenUseCase.deleteNotes(id).onEach { data->
            data.onSuccess {
                load()
            }
        }.launchIn(viewModelScope)
    }
}