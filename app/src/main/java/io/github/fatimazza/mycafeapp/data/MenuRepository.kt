package io.github.fatimazza.mycafeapp.data

import io.github.fatimazza.mycafeapp.model.FakeMenuDataSource
import io.github.fatimazza.mycafeapp.model.OrderMenu
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MenuRepository {

    private val orderMenus = mutableListOf<OrderMenu>()

    init {
        if (orderMenus.isEmpty()){
            FakeMenuDataSource.dummyMenus.forEach{
                orderMenus.add(OrderMenu(it, 0))
            }
        }
    }

    fun getAllMenus(): Flow<List<OrderMenu>> {
        return flowOf(orderMenus)
    }

    fun getOrderMenuById(menuId: Long): OrderMenu {
        return orderMenus.first {
            it.menu.id == menuId
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
