package com.falikiali.menurecipeapp.domain.usecase

import com.falikiali.menurecipeapp.domain.model.Area
import com.falikiali.menurecipeapp.domain.model.Category
import com.falikiali.menurecipeapp.domain.model.Menu
import com.falikiali.menurecipeapp.domain.model.Recipe
import com.falikiali.menurecipeapp.domain.repository.MenuRepository
import com.falikiali.menurecipeapp.helper.ResultState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImplMenuUseCase @Inject constructor(private val menuRepository: MenuRepository): MenuUseCase {

    /**
     * Remote
     */
    override suspend fun getCategories(): Flow<ResultState<List<Category>>> {
        return menuRepository.getCategories()
    }

    override suspend fun getAreas(): Flow<ResultState<List<Area>>> {
        return menuRepository.getAreas()
    }

    override suspend fun getMenusByCategory(category: String): Flow<ResultState<List<Menu>>> {
        return menuRepository.getMenusByCategory(category)
    }

    override suspend fun getMenusByArea(area: String): Flow<ResultState<List<Menu>>> {
        return menuRepository.getMenusByArea(area)
    }

    override suspend fun searchMenu(search: String): Flow<ResultState<List<Menu>>> {
        return menuRepository.searchMenu(search)
    }

    override suspend fun getRecipe(id: String): Flow<ResultState<Recipe>> {
        return menuRepository.getRecipe(id)
    }


    /**
     * Local
     */
    override suspend fun addToBookmark(menu: Menu) {
        return menuRepository.addToBookmark(menu)
    }

    override suspend fun removeFromBookmark(menu: Menu) {
        return menuRepository.removeFromBookmark(menu)
    }

    override fun getAllMenu(): Flow<List<Menu>> {
        return menuRepository.getAllMenu()
    }

    override fun checkBookmarkedMenu(id: String): Flow<Boolean> {
        return menuRepository.checkBookmarkedMenu(id)
    }

}