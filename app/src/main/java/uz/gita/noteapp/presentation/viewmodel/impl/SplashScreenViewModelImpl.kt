package uz.gita.noteapp.presentation.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.noteapp.presentation.viewmodel.SplashScreenViewModel
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModelImpl @Inject constructor() : SplashScreenViewModel, ViewModel() {
    override val openNextScreenViewModel = MutableLiveData<Unit>()

    init {
        viewModelScope.launch {
            delay(2000)
            openNextScreenViewModel.postValue(Unit)
        }
    }

}