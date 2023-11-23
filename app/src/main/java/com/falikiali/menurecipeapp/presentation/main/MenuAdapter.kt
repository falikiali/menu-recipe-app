package com.falikiali.menurecipeapp.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.falikiali.menurecipeapp.databinding.LayoutListMenuBinding
import com.falikiali.menurecipeapp.domain.model.Menu

class MenuAdapter: ListAdapter<Menu, MenuAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Menu>() {
            override fun areItemsTheSame(oldItem: Menu, newItem: Menu): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Menu, newItem: Menu): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuAdapter.ViewHolder {
        val binding = LayoutListMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: LayoutListMenuBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Menu) {
            with(binding) {
                tvMenuName.text = data.name
            }

            Glide.with(itemView)
                .load(data.thumbnail)
                .into(binding.ivThumbnail)
        }
    }
}