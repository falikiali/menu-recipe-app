package com.falikiali.menurecipeapp.data.remote.api

import com.falikiali.menurecipeapp.data.remote.dto.AreasResponse
import com.falikiali.menurecipeapp.data.remote.dto.CategoriesResponse
import com.falikiali.menurecipeapp.data.remote.dto.MenusResponse
import com.falikiali.menurecipeapp.data.remote.dto.RecipeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("list.php")
    suspend fun getCategories(@Query("c") c: String = "list"): Response<CategoriesResponse>

    @GET("list.php")
    suspend fun getAreas(@Query("a") a: String = "list"): Response<AreasResponse>

    @GET("filter.php")
    suspend fun getMenusByCategory(@Query("c") c: String): Response<MenusResponse>

    @GET("filter.php")
    suspend fun getMenusByArea(@Query("a") a: String): Response<MenusResponse>

    @GET("lookup.php")
    suspend fun getRecipe(@Query("i") i: String): Response<RecipeResponse>

    @GET("search.php")
    suspend fun searchMenu(@Query("s") s: String): Response<MenusResponse>

}