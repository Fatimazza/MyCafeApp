package io.github.fatimazza.mycafeapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.fatimazza.mycafeapp.data.MenuRepository
import io.github.fatimazza.mycafeapp.ui.screen.detail.DetailViewModel
import io.github.fatimazza.mycafeapp.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: MenuRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}
