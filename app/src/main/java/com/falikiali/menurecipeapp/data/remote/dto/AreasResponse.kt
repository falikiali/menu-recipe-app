package com.falikiali.menurecipeapp.data.remote.dto

import com.falikiali.menurecipeapp.domain.model.Area
import com.google.gson.annotations.SerializedName

data class AreasResponse(
    @field:SerializedName("meals") val meals: List<ItemAreaResponse>
)

data class ItemAreaResponse(
    @field:SerializedName("strArea") val name: String
) {
    fun toDomain(): Area {
        return Area(name)
    }
}
