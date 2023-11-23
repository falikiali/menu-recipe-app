package com.falikiali.menurecipeapp.data

import com.falikiali.menurecipeapp.data.local.dao.MenuDao
import com.falikiali.menurecipeapp.data.remote.api.ApiService
import com.falikiali.menurecipeapp.domain.model.Area
import com.falikiali.menurecipeapp.domain.model.Category
import com.falikiali.menurecipeapp.domain.model.Menu
import com.falikiali.menurecipeapp.domain.model.Recipe
import com.falikiali.menurecipeapp.domain.repository.MenuRepository
import com.falikiali.menurecipeapp.helper.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import javax.inject.Inject

class ImplMenuRepository @Inject constructor(private val apiService: ApiService, private val menuDao: MenuDao): MenuRepository {

    /**
     * Remote
     */
    override suspend fun getCategories(): Flow<ResultState<List<Category>>> {
        return flow {
            emit(ResultState.Loading)
            try {
                val response = apiService.getCategories()
                val data = response.body()?.meals?.map {
                    it.toDomain()
                }
                emit(ResultState.Success(data!!))
            } catch (e: HttpException) {
                emit(ResultState.Failed(e.message.toString(), 0))
            } catch (e: Throwable) {
                emit(ResultState.Failed(e.message.toString(), 0))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getAreas(): Flow<ResultState<List<Area>>> {
        return flow {
            emit(ResultState.Loading)
            try {
                val response = apiService.getAreas()
                val data = response.body()?.meals?.map {
                    it.toDomain()
                }
                emit(ResultState.Success(data!!))
            } catch (e: HttpException) {
                emit(ResultState.Failed(e.message.toString(), 0))
            } catch (e: Throwable) {
                emit(ResultState.Failed(e.message.toString(), 0))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getMenusByCategory(category: String): Flow<ResultState<List<Menu>>> {
        return flow {
            emit(ResultState.Loading)
            try {
                val response = apiService.getMenusByCategory(category)
                val data = response.body()?.meals?.map {
                    it.toDomain()
                }
                emit(ResultState.Success(data!!))
            } catch (e: HttpException) {
                emit(ResultState.Failed(e.message.toString(), 0))
            } catch (e: Throwable) {
                emit(ResultState.Failed(e.message.toString(), 0))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getMenusByArea(area: String): Flow<ResultState<List<Menu>>> {
        return flow {
            emit(ResultState.Loading)
            try {
                val response = apiService.getMenusByArea(area)
                val data = response.body()?.meals?.map {
                    it.toDomain()
                }
                emit(ResultState.Success(data!!))
            } catch (e: HttpException) {
                emit(ResultState.Failed(e.message.toString(), 0))
            } catch (e: Throwable) {
                emit(ResultState.Failed(e.message.toString(), 0))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun searchMenu(search: String): Flow<ResultState<List<Menu>>> {
        return flow {
            emit(ResultState.Loading)
            try {
                val response = apiService.searchMenu(search)
                val data = response.body()?.meals?.map {
                    it.toDomain()
                }
                emit(ResultState.Success(data!!))
            } catch (e: HttpException) {
                emit(ResultState.Failed(e.message.toString(), 0))
            } catch (e: Throwable) {
                emit(ResultState.Failed(e.message.toString(), 0))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getRecipe(id: Int): Flow<ResultState<Recipe>> {
        return flow {
            emit(ResultState.Loading)
            try {
                val response = apiService.getRecipe(id)
                val data = response.body()?.toDomain()
                emit(ResultState.Success(data!!))
            } catch (e: HttpException) {
                emit(ResultState.Failed(e.message.toString(), 0))
            } catch (e: Throwable) {
                emit(ResultState.Failed(e.message.toString(), 0))
            }
        }.flowOn(Dispatchers.IO)
    }


    /**
     * Local
     */
    override suspend fun addToBookmark(menu: Menu) {
        menuDao.addToBookmark(menu.toEntity())
    }

    override suspend fun removeFromBookmark(menu: Menu) {
        menuDao.removeFromBookmark(menu.toEntity())
    }

    override fun getAllMenu(): Flow<List<Menu>> {
        return menuDao.getAll().map { list ->
            list.map { menu ->
                menu.toDomain()
            }
        }
    }

    override fun checkBookmarkedMenu(id: String): Flow<Boolean> {
        return menuDao.checkMarkedMenu(id)
    }

}