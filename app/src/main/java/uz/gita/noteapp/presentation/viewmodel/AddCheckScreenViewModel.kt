package uz.gita.noteapp.presentation.viewmodel

import uz.gita.noteapp.data.model.NoteModel
import uz.gita.noteapp.data.source.local.room.entity.NoteEntity

interface AddCheckScreenViewModel {
    fun addCheck(check: NoteModel)
    fun updateCheck(title:String,content:String,date:String,id:String,type:Boolean)
}