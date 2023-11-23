package com.falikiali.menurecipeapp.di

import android.content.Context
import androidx.room.Room
import com.falikiali.menurecipeapp.data.local.AppDatabase
import com.falikiali.menurecipeapp.data.local.dao.MenuDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "db_application"
        ).build()
    }

    @Singleton
    @Provides
    fun provideMenuDao(appDatabase: AppDatabase): MenuDao {
        return appDatabase.menuDao()
    }

}