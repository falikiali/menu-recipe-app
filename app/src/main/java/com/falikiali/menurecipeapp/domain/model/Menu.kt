package com.falikiali.menurecipeapp.domain.model

import com.falikiali.menurecipeapp.data.local.entity.MenuEntity

data class Menu(
    val id: String,
    val name: String,
    val thumbnail: String
) {
    fun toEntity(): MenuEntity {
        return MenuEntity(id, name, thumbnail)
    }
}
