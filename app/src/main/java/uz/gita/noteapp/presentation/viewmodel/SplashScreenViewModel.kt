package uz.gita.noteapp.presentation.viewmodel

import androidx.lifecycle.LiveData

interface SplashScreenViewModel {
    val openNextScreenViewModel:LiveData<Unit>
}