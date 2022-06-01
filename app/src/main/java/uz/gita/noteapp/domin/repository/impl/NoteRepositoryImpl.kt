package uz.gita.noteapp.domin.repository.impl

import uz.gita.noteapp.data.source.local.room.dao.NoteDao
import uz.gita.noteapp.data.source.local.room.entity.NoteEntity
import uz.gita.noteapp.domin.repository.NoteRepository
import javax.inject.Inject

class NoteRepositoryImp @Inject constructor(
    private val database: NoteDao
) : NoteRepository {
    override suspend fun getNotes(): List<NoteEntity> {
        return database.getNotes()
    }

    override suspend fun getNotesByQuery(query: String): List<NoteEntity> {
        return database.getByQuery(query)
    }


    override suspend fun addNotes(note: NoteEntity) {
        database.insertNotes(note)
    }

    override suspend fun deleteNote(id: String) {
        database.delete(id)
    }

    override suspend fun updateNote(title: String, content: String, data: String, id: String,type:Boolean) {
        database.update(title, content, data, id)
    }

}