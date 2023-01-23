package io.github.fatimazza.mycafeapp.model

import io.github.fatimazza.mycafeapp.R

object FakeMenuDataSource {
    val dummyMenus = listOf(
        Menu(1, R.drawable.menu_1, R.string.food_sushi, 35000),
        Menu(2, R.drawable.menu_2, R.string.food_toast, 15000),
        Menu(3, R.drawable.menu_3, R.string.food_pizza, 65000),
        Menu(4, R.drawable.menu_4, R.string.food_salad, 42500),
        Menu(5, R.drawable.menu_5, R.string.food_ramen, 65000),
        Menu(6, R.drawable.menu_6, R.string.food_coffee, 17000),
        Menu(7, R.drawable.menu_7, R.string.food_doughnut, 12500),
        Menu(8, R.drawable.menu_8, R.string.food_fruit, 45000),
        Menu(9, R.drawable.menu_9, R.string.food_pancake, 32500),
        Menu(10, R.drawable.menu_10, R.string.food_choco, 15500),
        Menu(11, R.drawable.menu_11, R.string.food_tea, 15000),
        Menu(12, R.drawable.menu_12, R.string.food_dessert, 13500),
    )
}
