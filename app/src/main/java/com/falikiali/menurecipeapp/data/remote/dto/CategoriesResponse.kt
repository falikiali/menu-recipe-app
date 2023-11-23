package com.falikiali.menurecipeapp.data.remote.dto

import com.falikiali.menurecipeapp.domain.model.Category
import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
    @field:SerializedName("meals") val meals: List<ItemCategoryResponse>
)

data class ItemCategoryResponse(
    @field:SerializedName("strCategory") val name: String
) {
    fun toDomain(): Category {
        return Category(name)
    }
}
