package com.falikiali.menurecipeapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.falikiali.menurecipeapp.domain.model.Menu

@Entity(tableName = "menus")
data class MenuEntity(
    @PrimaryKey @ColumnInfo("id") val id: String,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("thumbnail") val thumbnail: String
) {
    fun toDomain(): Menu {
        return Menu(id, name, thumbnail)
    }
}
