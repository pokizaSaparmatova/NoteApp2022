package uz.gita.noteapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.noteapp.domin.usecase.MainScreenUseCase
import uz.gita.noteapp.domin.usecase.impl.MainScreenUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface MainScreenUseCaseModule {
    @[Binds Singleton]
    fun getMainUseCase(impl:MainScreenUseCaseImpl):MainScreenUseCase
}