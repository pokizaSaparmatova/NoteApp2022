package uz.gita.noteapp.presentation.ui.screen

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.noteapp.R
import uz.gita.noteapp.data.model.NoteModel
import uz.gita.noteapp.data.source.local.room.entity.NoteEntity
import uz.gita.noteapp.databinding.ScreenMainBinding
import uz.gita.noteapp.presentation.ui.adapter.NoteAdapter
import uz.gita.noteapp.presentation.viewmodel.MainScreenViewModel
import uz.gita.noteapp.presentation.viewmodel.impl.MainScreenViewModelImpl

@AndroidEntryPoint
class MainScreen : Fragment(R.layout.screen_main) {
    private val binding by viewBinding(ScreenMainBinding::bind)
    private val viewModel: MainScreenViewModel by viewModels<MainScreenViewModelImpl>()
    private val handler = Handler(Looper.getMainLooper())
    private val noteAdapter = NoteAdapter()
    private val rotateOpen: Animation by lazy {
        AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.rotate_open_anim
        )
    }
    private val rotateClose: Animation by lazy {
        AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.rotate_close_anim
        )
    }
    private val toBottom: Animation by lazy {
        AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.to_bottom_anim
        )
    }
    private val fromBottom: Animation by lazy {
        AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.from_bottom_anim
        )
    }

    private var closed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        noteAdapter.setOnLongPressed {
            viewModel.deleteNote(it)
        }

        noteAdapter.setOnItemClickListener {
            Log.d("AAA","$it")
            if (it.type) {
                findNavController().navigate(
                    MainScreenDirections.actionMainScreenToAddCheckScreen2(
                        NoteModel(
                            it.title,
                            it.content,
                            date = it.date,
                            timeDate = it.dateTime
                        )
                    )
                )
            } else {
                findNavController().navigate(
                MainScreenDirections.actionMainScreenToAddNoteScreen(
                    NoteModel(
                        it.title,
                        it.content,
                        date = it.date,
                        timeDate = it.dateTime
                    )
                )
            )

        }}
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        viewModel.notesLiveData.observe(viewLifecycleOwner, notesObserver)

        viewModel.load()
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                handler.removeCallbacksAndMessages(null)
                viewModel.getNotesByQuery("%$query%")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                handler.removeCallbacksAndMessages(null)
                viewModel.getNotesByQuery("%$newText%")
                return true
            }

        })
        note.setOnClickListener {
            closed = false
            findNavController().navigate(
                MainScreenDirections.actionMainScreenToAddCheckScreen2(
                    NoteModel(isAddPressed = true)
                )
            )
        }
        check.setOnClickListener {
            Log.d("HHH", "Clicked")
            closed = false
            findNavController().navigate(
                MainScreenDirections.actionMainScreenToAddNoteScreen(
                    NoteModel(isAddPressed = true)
                )
            )
        }
        binding.addF.setOnClickListener {
            if (!closed) {
                container.setBackgroundResource(R.drawable.trasparent_bg)
            } else {
                container.setBackgroundResource(R.drawable.bg)
            }
            onAddButtonClick()
        }
    }


    private val notesObserver = Observer<List<NoteEntity>> {

        noteAdapter.submitList(it as ArrayList<NoteEntity>)
        binding.noteList.adapter = noteAdapter
        binding.noteList.layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
    }

    private fun onAddButtonClick() {

        setVisibility(closed)
        setAnimation(closed)
        closed = !closed

    }

    private fun setVisibility(closed: Boolean) = with(binding) {
        if (!closed) {
            editF.visibility = View.VISIBLE
            binding.settingF.visibility = View.VISIBLE
        } else {
            binding.editF.visibility = View.INVISIBLE
            binding.settingF.visibility = View.INVISIBLE
        }
    }

    private fun setAnimation(closed: Boolean) {
        if (!closed) {
            binding.editF.startAnimation(fromBottom)
            binding.settingF.startAnimation(fromBottom)
            binding.addF.startAnimation(rotateOpen)
        } else {
            binding.editF.startAnimation(toBottom)
            binding.settingF.startAnimation(toBottom)
            binding.addF.startAnimation(rotateClose)
        }
    }
}