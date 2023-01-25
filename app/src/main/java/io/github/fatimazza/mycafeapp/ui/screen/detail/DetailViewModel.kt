package io.github.fatimazza.mycafeapp.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.fatimazza.mycafeapp.data.MenuRepository
import io.github.fatimazza.mycafeapp.model.Menu
import io.github.fatimazza.mycafeapp.model.OrderMenu
import io.github.fatimazza.mycafeapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: MenuRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<OrderMenu>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<OrderMenu>>
        get() = _uiState

    fun getMenuById(menuId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getOrderMenuById(menuId))
        }
    }

    fun addToCart(menu: Menu, count: Int) {
        viewModelScope.launch {
            repository.updateOrderMenu(menu.id, count)
        }
    }
}
