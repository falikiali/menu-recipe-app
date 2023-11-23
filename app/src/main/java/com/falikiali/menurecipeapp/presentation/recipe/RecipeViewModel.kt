package com.falikiali.menurecipeapp.presentation.recipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falikiali.menurecipeapp.domain.model.Menu
import com.falikiali.menurecipeapp.domain.model.Recipe
import com.falikiali.menurecipeapp.domain.usecase.MenuUseCase
import com.falikiali.menurecipeapp.helper.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(private val menuUseCase: MenuUseCase): ViewModel() {

    private val _recipeState = MutableLiveData<ResultState<Recipe>>()
    val recipeState: LiveData<ResultState<Recipe>> get() = _recipeState

    private val _isRecipeBookmarked = MutableLiveData<Boolean>()
    val isRecipeBookmarked: LiveData<Boolean> get() = _isRecipeBookmarked

    private val _bookmarkState = MutableLiveData<BookmarkState>()
    val bookmarkState: LiveData<BookmarkState> get() = _bookmarkState

    fun getRecipe(id: String) {
        viewModelScope.launch {
            menuUseCase.getRecipe(id).collect {
                _recipeState.postValue(it)
            }
        }
    }

    fun checkRecipeBookmark(id: String) {
        viewModelScope.launch {
            menuUseCase.checkBookmarkedMenu(id).collect {
                _isRecipeBookmarked.value = it
            }
        }
    }

    fun updateRecipeBookmark(menu: Menu) {
        if (isRecipeBookmarked.value == true) {
            viewModelScope.launch { menuUseCase.removeFromBookmark(menu) }
        } else {
            viewModelScope.launch { menuUseCase.addToBookmark(menu) }
        }

        _isRecipeBookmarked.value = !isRecipeBookmarked.value!!
        _bookmarkState.value = BookmarkState(!isRecipeBookmarked.value!!, menu.name)
    }

}

data class BookmarkState(
    val state: Boolean,
    val menu: String
)