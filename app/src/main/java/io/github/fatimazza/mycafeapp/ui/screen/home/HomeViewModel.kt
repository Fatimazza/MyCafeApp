package io.github.fatimazza.mycafeapp.ui.screen.home

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.fatimazza.mycafeapp.data.MenuRepository
import io.github.fatimazza.mycafeapp.model.OrderMenu
import io.github.fatimazza.mycafeapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: MenuRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<OrderMenu>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<OrderMenu>>>
        get() = _uiState

    fun getAllMenus() {
        viewModelScope.launch {
            repository.getAllMenus()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderMenus ->
                    _uiState.value = UiState.Success(orderMenus)
                }
        }
    }

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    private var _searchNotFound = mutableStateOf(false)
    val searchNotFound: State<Boolean> get() = _searchNotFound

    fun searchMenu(newQuery: String, context: Context) {
        viewModelScope.launch {
            _query.value = newQuery
            repository.searchMenu(_query.value, context)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderMenus ->
                    _uiState.value = UiState.Success(orderMenus.sortedBy { it.menu.id })
                    _searchNotFound.value = orderMenus.isEmpty()
                }
        }
    }
}
