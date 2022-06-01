package uz.gita.noteapp.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.gita.noteapp.R
import uz.gita.noteapp.data.model.TagModel
import uz.gita.noteapp.databinding.ItemTagBinding

class TagAdapter : RecyclerView.Adapter<TagAdapter.Holder>() {
    private var onItemClickListener: (TagModel)? = null

    private val data = arrayListOf<TagModel>(
        TagModel("#inspiration", false),
        TagModel("#personal", false),
        TagModel("#work", false),
    )

    inner class Holder(val binding: ItemTagBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.checkBox.setOnClickListener {
                if (binding.checkBox.isChecked) {
                    data[absoluteAdapterPosition].isChacked = true
                } else {
                    data[absoluteAdapterPosition].isChacked = false
                }
            }
        }

        fun bind() = with(binding) {
            val item = data[absoluteAdapterPosition]
            tagName.text = item.tagName
            if (item.isChacked) checkBox.isChecked = true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder = Holder(
        ItemTagBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.item_tag, parent, false)
        )
    )

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind()

    override fun getItemCount(): Int = data.size

    fun addItem(item: TagModel) {
        data.add(item)
    }

    fun getSelectedItems(): String {
        val st = StringBuilder()
        data.forEach {
            if (it.isChacked) st.append(it.tagName + "=")
        }
        return st.toString()
    }
}