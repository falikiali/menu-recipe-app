package com.falikiali.menurecipeapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.falikiali.menurecipeapp.data.local.entity.MenuEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MenuDao {

    @Query("SELECT * FROM menus")
    fun getAll(): Flow<List<MenuEntity>>

    @Query("SELECT EXISTS(SELECT * FROM menus WHERE id = :id)")
    fun checkMarkedMenu(id: String): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToBookmark(menuEntity: MenuEntity)

    @Delete
    suspend fun removeFromBookmark(menuEntity: MenuEntity)

}