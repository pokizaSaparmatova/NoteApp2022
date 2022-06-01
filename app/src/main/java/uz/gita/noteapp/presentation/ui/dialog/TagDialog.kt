package uz.gita.noteapp.presentation.ui.dialog

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.noteapp.R
import uz.gita.noteapp.data.model.TagModel
import uz.gita.noteapp.databinding.DialogTagBinding
import uz.gita.noteapp.presentation.ui.adapter.TagAdapter


class TagDialog : DialogFragment(R.layout.dialog_tag) {
    private val binding by viewBinding(DialogTagBinding::bind)
    private val recycler = TagAdapter()
    private var onDoneClickListener:((String)->Unit)?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.DialogStyle)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        binding.list.adapter = recycler
        binding.list.layoutManager = LinearLayoutManager(requireContext())
        addTag.setOnClickListener {
            val tag = editText.text.toString()
            if (tag.isNotEmpty()) {
                recycler.addItem(TagModel("#" + tag, false))
                recycler.notifyDataSetChanged()
                list.adapter = recycler
                list.layoutManager = LinearLayoutManager(requireContext())
                editText.setText("")
            }
        }

        binding.done.setOnClickListener {
            onDoneClickListener?.invoke(recycler.getSelectedItems())
            this@TagDialog.dismiss()
        }
    }


    fun setOnDoneClickListener(bl:((String)->Unit)){
        onDoneClickListener = bl
    }

}