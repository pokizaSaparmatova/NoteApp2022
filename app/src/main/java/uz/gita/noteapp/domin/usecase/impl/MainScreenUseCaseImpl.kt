package uz.gita.noteapp.domin.usecase.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.noteapp.data.source.local.room.entity.NoteEntity
import uz.gita.noteapp.domin.repository.NoteRepository
import uz.gita.noteapp.domin.usecase.MainScreenUseCase
import javax.inject.Inject

class MainScreenUseCaseImpl @Inject constructor(
    private val repository: NoteRepository
) : MainScreenUseCase {
    override fun getNotes(): Flow<Result<List<NoteEntity>>> = flow<Result<List<NoteEntity>>> {
        emit(Result.success(repository.getNotes()))
    }.flowOn(Dispatchers.IO)

    override fun getNotesByQuery(query: String): Flow<Result<List<NoteEntity>>> =
        flow<Result<List<NoteEntity>>> {
            emit(Result.success(repository.getNotesByQuery(query)))
        }.flowOn(Dispatchers.IO)

    override fun deleteNotes(id: String): Flow<Result<Unit>> = flow {
        emit(Result.success(repository.deleteNote(id)))
    }.flowOn(Dispatchers.IO)




}