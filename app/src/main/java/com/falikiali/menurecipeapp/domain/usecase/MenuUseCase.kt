package com.falikiali.menurecipeapp.domain.usecase

import com.falikiali.menurecipeapp.domain.model.Area
import com.falikiali.menurecipeapp.domain.model.Category
import com.falikiali.menurecipeapp.domain.model.Menu
import com.falikiali.menurecipeapp.domain.model.Recipe
import com.falikiali.menurecipeapp.helper.ResultState
import kotlinx.coroutines.flow.Flow

interface MenuUseCase {

    /**
     * Remote
     */
    suspend fun getCategories(): Flow<ResultState<List<Category>>>
    suspend fun getAreas(): Flow<ResultState<List<Area>>>
    suspend fun getMenusByCategory(category: String): Flow<ResultState<List<Menu>>>
    suspend fun getMenusByArea(area: String): Flow<ResultState<List<Menu>>>
    suspend fun searchMenu(search: String): Flow<ResultState<List<Menu>>>
    suspend fun getRecipe(id: Int): Flow<ResultState<Recipe>>

    /**
     * Local
     */
    suspend fun addToBookmark(menu: Menu)
    suspend fun removeFromBookmark(menu: Menu)
    fun getAllMenu(): Flow<List<Menu>>
    fun checkBookmarkedMenu(id: String): Flow<Boolean>

}