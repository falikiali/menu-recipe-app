package com.falikiali.menurecipeapp.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falikiali.menurecipeapp.domain.model.Category
import com.falikiali.menurecipeapp.domain.model.Menu
import com.falikiali.menurecipeapp.domain.usecase.MenuUseCase
import com.falikiali.menurecipeapp.helper.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val menuUseCase: MenuUseCase): ViewModel() {

    private val _categoriesState = MutableLiveData<ResultState<List<Category>>>()
    val categoriesState: LiveData<ResultState<List<Category>>> get() = _categoriesState

    private val _searchState = MutableLiveData<ResultState<List<Menu>>>()
    val searchState: LiveData<ResultState<List<Menu>>> get() = _searchState

    private val _menuByCategoryState = MutableLiveData<ResultState<List<Menu>>>()
    val menuByCategoryState: LiveData<ResultState<List<Menu>>> get() = _menuByCategoryState

    fun getCategories() {
        viewModelScope.launch {
            menuUseCase.getCategories().collect {
                _categoriesState.postValue(it)
            }
        }
    }

    fun searchMenu(search: String) {
        viewModelScope.launch {
            menuUseCase.searchMenu(search).collect {
                _searchState.postValue(it)
            }
        }
    }

    fun getMenuByCategory(category: String) {
        viewModelScope.launch {
            menuUseCase.getMenusByCategory(category).collect {
                _menuByCategoryState.postValue(it)
            }
        }
    }

}