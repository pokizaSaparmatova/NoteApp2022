package uz.gita.noteapp.presentation.ui.screen

import android.os.Bundle
import android.text.SpannableString
import android.text.style.StrikethroughSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.ScrollView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import by.kirich1409.viewbindingdelegate.internal.findRootView
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import org.wordpress.aztec.Aztec
import org.wordpress.aztec.AztecText
import org.wordpress.aztec.ITextFormat
import org.wordpress.aztec.toolbar.IAztecToolbarClickListener
import uz.gita.noteapp.R
import uz.gita.noteapp.data.model.NoteModel
import uz.gita.noteapp.data.source.local.room.entity.NoteEntity
import uz.gita.noteapp.databinding.ScreenCheckBinding
import uz.gita.noteapp.presentation.viewmodel.AddCheckScreenViewModel
import uz.gita.noteapp.presentation.viewmodel.impl.AddCheckScreenViewModelImpl
import java.lang.StringBuilder

@AndroidEntryPoint
class AddCheckScreen : Fragment(R.layout.screen_check) {
    private val binding by viewBinding(ScreenCheckBinding::bind)
    private val args by navArgs<AddCheckScreenArgs>()
    private val viewModel: AddCheckScreenViewModel by viewModels<AddCheckScreenViewModelImpl>()
    private var bool = true
    private var content: EditText? = null
    private var title: EditText? = null
    val checkList = ArrayList<View>()
    private var sb = StringBuilder()
    private var sb1 = StringBuilder()
    private var isCheck = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val note = args.note

        binding.text.setOnClickListener {
            val itemCheck = LayoutInflater.from(requireContext())
                .inflate(R.layout.item_check, binding.container, false) as LinearLayoutCompat
            content = itemCheck.findViewById(R.id.editText)
            checkList.add(itemCheck)
            binding.container.addView(itemCheck)
            itemCheck.getChildAt(2).setOnClickListener {
                (itemCheck.parent as LinearLayoutCompat).removeView(itemCheck)
                checkList.remove(itemCheck)

            }
            init()

        }

        binding.source.setText(note.title)

        val checkItemList = note.content.split("#")
//        if (checkItemList.contains("0")) {
        for (i in 0 until checkItemList.size - 1) {
            val itemCheck = LayoutInflater.from(requireContext())
                .inflate(R.layout.item_check, binding.container, false) as LinearLayoutCompat
            content = itemCheck.findViewById(R.id.editText)
            binding.container.addView(itemCheck)
            content?.setText(checkItemList[i])
            checkList.add(itemCheck)
            itemCheck.getChildAt(2).setOnClickListener {
                (itemCheck.parent as LinearLayoutCompat).removeView(itemCheck)
                checkList.remove(itemCheck)
            }
            init()
        }

//        }
//        else{
//
//        }

        binding.save.setOnClickListener {
            if (note.isAddPressed) {
                val title = binding.source.text.toString()
                for (i in 0 until checkList.size) {
                    val edit = checkList[i].findViewById<EditText>(R.id.editText)
                    val v = checkList[i] as LinearLayoutCompat
                    val check = v.findViewById<CheckBox>(R.id.checkBox)
                    if(check.isChecked){
                      sb1.append("1").append("#")
                    }
                    else sb1.append("0").append("#")
                    sb.append(edit.text.toString()).append("#")
                    Log.d("AAAA", "${sb1.toString()}")
                    Log.d("AAAA", "${sb.toString()}")


                }
                viewModel.addCheck(
                    NoteModel(
                        title = title,
                        content = sb.toString(),
                        check = sb1.toString()
                        )
                )


            } else {

                val title = binding.source.text.toString()
                for (i in 0 until checkList.size) {
                    val edit = checkList[i].findViewById<EditText>(R.id.editText)
                    val v = checkList[i] as LinearLayoutCompat
                    val check = v.findViewById<CheckBox>(R.id.checkBox)
                    if (check.isChecked) {
                        sb.append(edit.text.toString()).append("#").append("$").append("1")
                        Log.d("www", "${check.isChecked}")
                        Log.d("www", "${sb}")

                    } else {
                        sb1.append(edit.text.toString()).append("#").append("$").append("0")
                        Log.d("www", "${sb}")
                    }

                }
                viewModel.updateCheck(
                    title = title,
                    content = sb.toString() + sb1.toString(),
                    date = "",
                    id = note.timeDate,
                    type = true
                )

            }
            findNavController().popBackStack()
            bool = false
        }

    }

    private fun init() {

        for (i in 0 until checkList.size) {

            val item = checkList[i]
            var txt = ""
            val editText = item.findViewById<EditText>(R.id.editText)
            val checkBox = item.findViewById<CheckBox>(R.id.checkBox)

            checkBox.setOnCheckedChangeListener { button, isChecked ->
                isCheck = isChecked
                if (isChecked) {
                    val spanText = SpannableString(editText.text)
                    spanText.setSpan(
                        StrikethroughSpan(),
                        0,
                        editText.text.length,
                        SpannableString.SPAN_INCLUSIVE_EXCLUSIVE
                    )
                    Log.d("aaa", "$isChecked")
                    editText!!.setText(spanText)
                    txt = editText.text.toString()
                } else {

                    val spanText = SpannableString(editText.text)
                    val str: String = spanText.toString()
                    editText.setText(str)
                }
            }

        }
    }

    override fun onStop() {
        super.onStop()
        val note = args.note
        val title = binding.source.text.toString()

        if (bool) {
            if (note.isAddPressed && (binding.source.text!!.isNotEmpty() || sb.toString()
                    .isNotEmpty())
            ) {
                for (i in 0 until checkList.size) {
                    val edit = checkList[i].findViewById<EditText>(R.id.editText)
                    sb.append(edit.text.toString()).append("#")
                    Log.d("zzz", "${sb}")
                }
                viewModel.addCheck(NoteModel(title = title, content = sb.toString()))
            } else {
                Log.d("ddd", "$bool")
                val title = binding.source.text.toString()
                for (i in 0 until checkList.size) {
                    val edit = checkList[i].findViewById<EditText>(R.id.editText)
                    sb.append(edit.text.toString()).append("#")

                }
                viewModel.updateCheck(
                    title = title,
                    content = sb.toString(),
                    date = "",
                    id = note.timeDate,
                    type = true
                )
            }
        }
    }

}

