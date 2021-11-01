package com.example.notesapp2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*

class RVAdapter  (val activity: MainActivity) : RecyclerView.Adapter<RVAdapter.ItemViewHolder>() {
    private var itemList = emptyList<Notes>()

//    private val myViewModel by lazy { ViewModelProvider(activity).get(MyViewModel::class.java) }
//    private val notesDao by lazy { NoteDB.getDatabase(activity).notesDao() }

    class ItemViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var items = itemList[position]

        holder.itemView.apply {
            tvItems.text = items.note

            bEdit.setOnClickListener{
                activity.dialog(items.pk)

            }

            bDel.setOnClickListener{
                activity.myViewModel.deleteNote(items.pk)
            }

        }
    }

    override fun getItemCount() = itemList.size

    fun update(itemList : List<Notes>){
        this.itemList = itemList
        notifyDataSetChanged()
    }
}