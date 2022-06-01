package uz.gita.noteapp.domin.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.noteapp.data.source.local.room.entity.NoteEntity


interface MainScreenUseCase {
    fun getNotes(): Flow<Result<List<NoteEntity>>>
    fun getNotesByQuery(query: String): Flow<Result<List<NoteEntity>>>
    fun deleteNotes(id: String): Flow<Result<Unit>>


}