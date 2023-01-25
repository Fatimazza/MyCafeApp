package io.github.fatimazza.mycafeapp.ui.screen.cart

import io.github.fatimazza.mycafeapp.model.OrderMenu


data class CartState(
    val orderMenu: List<OrderMenu>,
    val totalPrice: Int
)
