package io.github.fatimazza.mycafeapp

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import io.github.fatimazza.mycafeapp.ui.navigation.NavigationItem
import io.github.fatimazza.mycafeapp.ui.navigation.Screen
import io.github.fatimazza.mycafeapp.ui.theme.MyCafeAppTheme

@Composable
fun MyCafeApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    Scaffold(
        bottomBar = {
            BottomBar(navController)
        },
        modifier = modifier
    ) { innerPadding ->
        innerPadding.toString()
    }
}

@Composable
private fun BottomBar(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    val navigationItems = listOf(
        NavigationItem(
            title = stringResource(R.string.menu_home),
            icon = Icons.Default.Home,
            screen = Screen.Home
        ),
        NavigationItem(
            title = stringResource(R.string.menu_cart),
            icon = Icons.Default.ShoppingCart,
            screen = Screen.Cart
        ),
        NavigationItem(
            title = stringResource(R.string.menu_profile),
            icon = Icons.Default.Person,
            screen = Screen.Profile
        )
    )

    BottomNavigation {
        navigationItems.map { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                selected = true,
                onClick = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyCafeAppPreview() {
    MyCafeAppTheme {
        MyCafeApp(rememberNavController())
    }
}
