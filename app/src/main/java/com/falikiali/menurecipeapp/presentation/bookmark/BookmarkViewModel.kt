package com.falikiali.menurecipeapp.presentation.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falikiali.menurecipeapp.domain.model.Menu
import com.falikiali.menurecipeapp.domain.usecase.MenuUseCase
import com.falikiali.menurecipeapp.helper.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(private val menuUseCase: MenuUseCase): ViewModel() {

    private val _bookmarkMenuState = MutableLiveData<List<Menu>>()
    val bookmarkMenuState: LiveData<List<Menu>> get() = _bookmarkMenuState

    fun getAllBookmarkMenu() {
        viewModelScope.launch {
            menuUseCase.getAllBookmarkMenu().collect {
                _bookmarkMenuState.value = it
            }
        }
    }

}