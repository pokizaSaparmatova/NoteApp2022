package uz.gita.noteapp.domin.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.noteapp.data.source.local.room.entity.NoteEntity


interface AddNoteUseCase {
   fun addNote(note:NoteEntity): Flow<Result<Unit>>
   fun updateNote(title:String,content:String,date:String,id:String,type:Boolean):Flow<Result<Unit>>
}