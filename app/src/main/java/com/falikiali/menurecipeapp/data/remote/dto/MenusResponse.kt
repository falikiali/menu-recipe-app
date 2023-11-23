package com.falikiali.menurecipeapp.data.remote.dto

import com.falikiali.menurecipeapp.domain.model.Menu
import com.google.gson.annotations.SerializedName

data class MenusResponse(
    @field:SerializedName("meals") val meals: List<ItemMenuResponse>
)

data class ItemMenuResponse(
    @field:SerializedName("idMeal") val id: String,
    @field:SerializedName("strMeal") val name: String,
    @field:SerializedName("strMealThumb") val thumbnail: String
) {
    fun toDomain(): Menu {
        return Menu(id, name, thumbnail)
    }
}
