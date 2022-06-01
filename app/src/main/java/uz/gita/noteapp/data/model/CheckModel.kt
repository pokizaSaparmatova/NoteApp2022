package uz.gita.noteapp.data.model

import java.io.Serializable


data class CheckModel(
    val title: String = "",
    val isChecked: Boolean = false,
    val date: String = "",
    val content: String = "",
    val timeDate: String = "",
    val isAddPressed: Boolean = false
) : Serializable