package com.falikiali.menurecipeapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.falikiali.menurecipeapp.data.local.dao.MenuDao
import com.falikiali.menurecipeapp.data.local.entity.MenuEntity

@Database(entities = [MenuEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun menuDao(): MenuDao

}