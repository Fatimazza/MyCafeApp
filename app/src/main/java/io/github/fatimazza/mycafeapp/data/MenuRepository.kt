package io.github.fatimazza.mycafeapp.data

import io.github.fatimazza.mycafeapp.model.FakeMenuDataSource
import io.github.fatimazza.mycafeapp.model.OrderMenu

class MenuRepository {

    private val orderMenus = mutableListOf<OrderMenu>()

    init {
        if (orderMenus.isEmpty()){
            FakeMenuDataSource.dummyMenus.forEach{
                orderMenus.add(OrderMenu(it, 0))
            }
        }
    }

    companion object {
        @Volatile
        private var instance: MenuRepository? = null

        fun getInstance(): MenuRepository =
            instance ?: synchronized(this) {
                MenuRepository().apply {
                    instance = this
                }
            }
    }
}
