package com.falikiali.menurecipeapp.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.falikiali.menurecipeapp.databinding.LayoutListFilterBinding
import com.falikiali.menurecipeapp.domain.model.Category

class FilterCategoryAdapter(val onItemClick: (Category) -> Unit): ListAdapter<Category, FilterCategoryAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Category>() {
            override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterCategoryAdapter.ViewHolder {
        val binding = LayoutListFilterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilterCategoryAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: LayoutListFilterBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Category) {
            with(binding.btnFilter) {
                text = data.name
                setOnClickListener { onItemClick(data) }
            }
        }
    }

}