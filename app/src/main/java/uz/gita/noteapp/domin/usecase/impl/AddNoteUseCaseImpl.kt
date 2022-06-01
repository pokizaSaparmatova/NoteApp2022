package uz.gita.noteapp.domin.usecase.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.noteapp.data.source.local.room.entity.NoteEntity
import uz.gita.noteapp.domin.repository.NoteRepository
import uz.gita.noteapp.domin.usecase.AddNoteUseCase
import javax.inject.Inject

class AddNoteUseCaseImpl @Inject constructor(private val repository:NoteRepository):AddNoteUseCase {

    override  fun addNote(note:NoteEntity): Flow<Result<Unit>> = flow{
        emit(Result.success(repository.addNotes(note)))
    }.flowOn(Dispatchers.IO)

    override fun updateNote(
        title: String,
        content: String,
        date: String,
        id: String,type:Boolean
    ): Flow<Result<Unit>> = flow{
        emit(Result.success(repository.updateNote(title,content,date,id,type)))
    }.flowOn(Dispatchers.IO)


}