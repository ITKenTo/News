package com.example.newapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newapp.data.model.NewModel
import com.example.newapp.databinding.ItemNewBinding

class NewAppAdapter(
    private val list: List<NewModel>, private val context: Context, val onClick: (NewModel) -> Unit
) : RecyclerView.Adapter<NewAppAdapter.ViewHoder>() {
    inner class ViewHoder(var binding: ItemNewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHoder {
        return ViewHoder(ItemNewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHoder, position: Int) {

        val newModel = list[position]
        holder.itemView.setOnClickListener {
            onClick.invoke(list[position])
        }
        holder.binding.apply {
            Glide.with(context).load(newModel.urlToImage).into(img)
            tvTitle.text = newModel.title
            tvDes.text = newModel.description
        }
    }
}