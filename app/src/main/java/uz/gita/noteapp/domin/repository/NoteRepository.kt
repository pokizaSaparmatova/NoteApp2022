package uz.gita.noteapp.domin.repository

import uz.gita.noteapp.data.source.local.room.entity.NoteEntity

interface NoteRepository {

    suspend fun getNotes(): List<NoteEntity>
    suspend fun getNotesByQuery(query: String): List<NoteEntity>
    suspend fun addNotes(note: NoteEntity)
    suspend fun deleteNote(id: String)
    suspend fun updateNote(title: String, content: String, data: String, id: String,type:Boolean)
//
//    suspend fun getCheckList():List<CheckEntity>
//    suspend fun getCheckByQuery(query:String):List<CheckEntity>
//    suspend fun addCheckList(checks:CheckEntity)
//    suspend fun deleteCheckList(id:String)
//    suspend fun updateCheckList(title: String,content: String,data:String,isChecked:Boolean,id:String)

}