package com.falikiali.menurecipeapp.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.falikiali.menurecipeapp.databinding.LayoutListFilterBinding
import com.falikiali.menurecipeapp.domain.model.Area

class FilterAreaAdapter(val onItemClick: (Area) -> Unit): ListAdapter<Area, FilterAreaAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Area>() {
            override fun areItemsTheSame(oldItem: Area, newItem: Area): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Area, newItem: Area): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterAreaAdapter.ViewHolder {
        val binding = LayoutListFilterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilterAreaAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: LayoutListFilterBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Area) {
            with(binding.btnFilter) {
                text = data.name
                setOnClickListener { onItemClick(data) }
            }
        }
    }

}