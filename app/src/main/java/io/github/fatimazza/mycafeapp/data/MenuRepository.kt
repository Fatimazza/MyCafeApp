package io.github.fatimazza.mycafeapp.data

import io.github.fatimazza.mycafeapp.model.FakeMenuDataSource
import io.github.fatimazza.mycafeapp.model.OrderMenu
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

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

    fun updateOrderMenu(menuId: Long, newCountValue: Int): Flow<Boolean> {
        val index = orderMenus.indexOfFirst { it.menu.id == menuId }
        val result = if (index >= 0) {
            val orderMenu = orderMenus[index]
            orderMenus[index] =
                orderMenu.copy(menu = orderMenu.menu, count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getAddedOrderMenus(): Flow<List<OrderMenu>> {
        return getAllMenus()
            .map { orderMenus ->
                orderMenus.filter { orderMenu ->
                    orderMenu.count != 0
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
