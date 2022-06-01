package uz.gita.noteapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.noteapp.domin.repository.NoteRepository
import uz.gita.noteapp.domin.repository.impl.NoteRepositoryImp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NoteRepositoryModule {

    @[Binds Singleton]
    fun getNoteRepository(impl:NoteRepositoryImp):NoteRepository
}