package com.falikiali.menurecipeapp.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falikiali.menurecipeapp.domain.model.Area
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

    private val _areasState = MutableLiveData<ResultState<List<Area>>>()
    val areasState: LiveData<ResultState<List<Area>>> get() = _areasState

    private val _menuState = MutableLiveData<ResultState<List<Menu>>>()
    val menuState: LiveData<ResultState<List<Menu>>> get() = _menuState

    private val _filterCategoryState = MutableLiveData(true)
    val filterCategoryState: LiveData<Boolean> get() = _filterCategoryState

    fun getCategories() {
        viewModelScope.launch {
            menuUseCase.getCategories().collect {
                _categoriesState.postValue(it)
            }
        }
    }

    fun getAreas() {
        viewModelScope.launch {
            menuUseCase.getAreas().collect {
                _areasState.postValue(it)
            }
        }
    }

    fun searchMenu(search: String) {
        viewModelScope.launch {
            menuUseCase.searchMenu(search).collect {
                _menuState.postValue(it)
            }
        }
    }

    fun getMenuByCategory(category: String) {
        viewModelScope.launch {
            menuUseCase.getMenusByCategory(category).collect {
                _menuState.postValue(it)
            }
        }
    }

    fun getMenuByArea(area: String) {
        viewModelScope.launch {
            menuUseCase.getMenusByArea(area).collect {
                _menuState.postValue(it)
            }
        }
    }

    fun updateFilter() {
        _filterCategoryState.value = !filterCategoryState.value!!
    }

}