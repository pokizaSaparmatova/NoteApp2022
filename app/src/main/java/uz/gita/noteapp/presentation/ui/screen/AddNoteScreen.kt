package uz.gita.noteapp.presentation.ui.screen

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.richeditor.RichEditor
import org.wordpress.aztec.Aztec
import org.wordpress.aztec.AztecText
import org.wordpress.aztec.ITextFormat
import org.wordpress.aztec.toolbar.IAztecToolbarClickListener
import uz.gita.noteapp.R
import uz.gita.noteapp.data.model.NoteModel
import uz.gita.noteapp.databinding.ScreenAddNoteBinding
import uz.gita.noteapp.presentation.ui.dialog.TagDialog
import uz.gita.noteapp.presentation.viewmodel.AddNoteScreenViewModel
import uz.gita.noteapp.presentation.viewmodel.impl.AddNoteScreenViewModelImpl

@AndroidEntryPoint
class AddNoteScreen : Fragment(R.layout.screen_add_note) {
    private val binding by viewBinding(ScreenAddNoteBinding::bind)
    private val args by navArgs<AddNoteScreenArgs>()
    private val viewModel: AddNoteScreenViewModel by viewModels<AddNoteScreenViewModelImpl>()
    private var visual1: AztecText? = null
    private var bool = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        Aztec.with(binding.visual, binding.formattingToolbar, object : IAztecToolbarClickListener {

            override fun onToolbarCollapseButtonClicked() {

            }

            override fun onToolbarExpandButtonClicked() {

            }

            override fun onToolbarFormatButtonClicked(
                format: ITextFormat,
                isKeyboardShortcut: Boolean
            ) {

            }

            override fun onToolbarHeadingButtonClicked() {

            }

            override fun onToolbarHtmlButtonClicked() {

            }

            override fun onToolbarListButtonClicked() {

            }

            override fun onToolbarMediaButtonClicked(): Boolean = false
        })
        visual1 = binding.visual
        visual1?.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        visual1?.toPlainHtml()
        val note = args.note
        source.setText(note.title)
        visual1?.fromHtml(note.content)

        save.setOnClickListener {
            if (note.isAddPressed) {
                val title = source.text.toString()
                viewModel.addNote(NoteModel(title = title, content = visual1?.text.toString()))
            } else {
                viewModel.updateNote(
                    title = source.text.toString(),
                    content = visual1?.text.toString(),
                    date = note.timeDate,
                    id = "",
                    type = false
                )
            }
            findNavController().popBackStack()
            bool = false
        }

    }

    override fun onStop() {
        super.onStop()
        val note = args.note
        if (bool) {
            if (note.isAddPressed && (binding.source.text!!.isNotEmpty() || visual1!!.text.toString()
                    .isNotEmpty())
            ) {
                val title = binding.source.text.toString()
                viewModel.addNote(NoteModel(title = title, content = visual1?.text.toString()))
            } else {
                viewModel.updateNote(
                    title = binding.source.text.toString(),
                    content = visual1?.text.toString() ?: "",
                    date = note.timeDate,
                    id = "",
                    type = false
                )
                Log.d("iii","${note.date}")
            }
        }
    }

}