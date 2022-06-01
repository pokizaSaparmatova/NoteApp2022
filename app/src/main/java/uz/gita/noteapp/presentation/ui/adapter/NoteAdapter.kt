package uz.gita.noteapp.presentation.ui.adapter

import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import uz.gita.noteapp.R
import uz.gita.noteapp.data.source.local.room.entity.NoteEntity
import uz.gita.noteapp.databinding.ItemBinding

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.Holder>() {
    private var data = ArrayList<NoteEntity>()

    private var onItemClickListener: ((NoteEntity) -> Unit)? = null
    private var onLongPress: ((String) -> Unit)? = null

    inner class Holder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onItemClickListener?.invoke(data[absoluteAdapterPosition])
            }
            binding.root.setOnLongClickListener {
                onLongPress?.invoke(data[absoluteAdapterPosition].dateTime)
                true
            }
        }

        fun bind() {
            val list = data[absoluteAdapterPosition]
            if (list.type) {
                val content1 = list.content.split("#")
                val check = list.check.split("#")
                var sb = String()
                var sb1 = String()
                binding.lin.setBackgroundResource(R.color.viewColor)
                binding.view.setBackgroundResource(R.color.cardColor)
                binding.noteTitle.text = list.title

                sb = "<ul>"
                for (i in content1.indices) {
                    var a = content1[i]
                    if (a.isEmpty()) a = "<br>"
//                    if (check[i] == "1") {
//                        sb += "<li>\n<del>$a<\\del><\\li>"
//                    }
                }

                sb += "<\\ul>"

                binding.noteDescription.text = Html.fromHtml(sb)
                binding.noteDate.text = list.date
            } else {
                binding.noteTitle.text = list.title
                binding.noteDescription.text = Html.fromHtml(list.content)
                binding.noteDate.text = list.date
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder = Holder(
        ItemBinding.bind(
            LayoutInflater
                .from(parent.context).inflate(R.layout.item, parent, false)
        )
    )

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind()

    override fun getItemCount(): Int = data.size

    fun setOnItemClickListener(bl: (NoteEntity) -> Unit) {
        onItemClickListener = bl
    }

    fun submitList(list: ArrayList<NoteEntity>) {
        data.clear()
        data.addAll(list)
        data.reverse()
    }

    fun setOnLongPressed(block: (String) -> Unit) {
        onLongPress = block
    }
}