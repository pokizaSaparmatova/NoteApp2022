package uz.gita.noteapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.gita.noteapp.data.source.local.room.NoteDb
import uz.gita.noteapp.data.source.local.room.dao.NoteDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {
    @[Provides Singleton]
    fun getDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, NoteDb::class.java, "NoteDatabse")
            .fallbackToDestructiveMigration()
            .build()

    @[Provides Singleton]
    fun getDao(database: NoteDb): NoteDao = database.getDao()


}