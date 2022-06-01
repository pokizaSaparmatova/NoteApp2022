package uz.gita.noteapp.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.gita.noteapp.data.source.local.room.dao.NoteDao
import uz.gita.noteapp.data.source.local.room.entity.NoteEntity

@Database(entities = [NoteEntity::class], version = 2)
abstract class NoteDb :RoomDatabase(){
    abstract fun getDao():NoteDao
}