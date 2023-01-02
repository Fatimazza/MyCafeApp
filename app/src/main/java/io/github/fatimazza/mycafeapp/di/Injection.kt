package io.github.fatimazza.mycafeapp.di

import io.github.fatimazza.mycafeapp.data.MenuRepository

object Injection {

    fun provideRepository(): MenuRepository {
        return MenuRepository.getInstance()
    }

}
