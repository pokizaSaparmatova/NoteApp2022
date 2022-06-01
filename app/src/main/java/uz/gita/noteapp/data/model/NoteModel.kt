package uz.gita.noteapp.data.model

import androidx.room.PrimaryKey
import java.io.Serializable

data class NoteModel (
    val title: String="",
    val content: String="",
    var date:String="",
//    var tag:String="",
    var type:Boolean=false,
    val timeDate:String="",
    val check:String="",
    val isAddPressed:Boolean=false
        ):Serializable