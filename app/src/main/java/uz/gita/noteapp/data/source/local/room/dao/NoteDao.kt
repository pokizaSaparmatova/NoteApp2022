package uz.gita.noteapp.data.source.local.room.dao

import androidx.room.*
import uz.gita.noteapp.data.source.local.room.entity.NoteEntity

@Dao
interface NoteDao {
    @Query("SELECT * from NoteEntity")
    suspend fun getNotes(): List<NoteEntity>

    @Query("SELECT * FROM NoteEntity where title like :query")
    suspend fun getByQuery(query: String): List<NoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(note: NoteEntity)

    @Update()
    fun update(title: String, content: String, date: String, id: String)

    @Query("Delete from NoteEntity where date_time=:id")
    fun delete(id:String)

}