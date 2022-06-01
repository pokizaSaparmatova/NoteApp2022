package uz.gita.noteapp.presentation.ui.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.noteapp.R
import uz.gita.noteapp.presentation.viewmodel.SplashScreenViewModel
import uz.gita.noteapp.presentation.viewmodel.impl.SplashScreenViewModelImpl
@AndroidEntryPoint
class SplashScreen:Fragment(R.layout.screen_splash) {
    private val viewModel:SplashScreenViewModel by viewModels<SplashScreenViewModelImpl>()

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.openNextScreenViewModel.observe(this@SplashScreen, openObserver)
    }

    private val openObserver = Observer<Unit>{
        findNavController().navigate(SplashScreenDirections.actionSplashScreenToMainScreen())
    }


}