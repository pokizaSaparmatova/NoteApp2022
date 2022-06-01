package uz.gita.noteapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.noteapp.domin.usecase.AddNoteUseCase
import uz.gita.noteapp.domin.usecase.impl.AddNoteUseCaseImpl
import uz.gita.noteapp.presentation.viewmodel.impl.AddNoteScreenViewModelImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AddScreenUseCaseModule {
    @[Binds Singleton]
    fun getAppUseCase(impl:AddNoteUseCaseImpl):AddNoteUseCase
}