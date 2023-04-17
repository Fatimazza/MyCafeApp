package io.github.fatimazza.mycafeapp

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import io.github.fatimazza.mycafeapp.ui.navigation.NavigationItem
import io.github.fatimazza.mycafeapp.ui.navigation.Screen
import io.github.fatimazza.mycafeapp.ui.screen.cart.CartScreen
import io.github.fatimazza.mycafeapp.ui.screen.detail.DetailScreen
import io.github.fatimazza.mycafeapp.ui.screen.home.HomeScreen
import io.github.fatimazza.mycafeapp.ui.screen.profile.ProfileScreen
import io.github.fatimazza.mycafeapp.ui.theme.MyCafeAppTheme

@Composable
fun MyCafeApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.DetailMenu.route) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { menuId ->
                        navController.navigate(Screen.DetailMenu.createRoute(menuId))
                    }
                )
            }
            composable(Screen.Cart.route) {
                val context = LocalContext.current
                CartScreen(
                    onOrderButtonClicked = { message ->
                        shareOrder(context, message)
                    }
                )
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
            composable(
                route = Screen.DetailMenu.route,
                arguments = listOf(navArgument("menuId") { type = NavType.LongType }),
            ) {
                val id = it.arguments?.getLong("menuId") ?: -1L
                DetailScreen(
                    menuId = id,
                    navigateBack = {
                        navController.navigateUp()
                    },
                    navigateToCart = {
                        navController.popBackStack()
                        navController.navigate(Screen.Cart.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}

private fun shareOrder(context: Context, summary: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.share_subject))
        putExtra(Intent.EXTRA_TEXT, summary)
    }

    context.startActivity(
        Intent.createChooser(
            intent,
            context.getString(R.string.share_subject)
        )
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun BottomBar(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier.semantics {
    testTagsAsResourceId = true
}
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val navigationItems = listOf(
        NavigationItem(
            title = stringResource(R.string.menu_home),
            icon = Icons.Default.Home,
            screen = Screen.Home,
            modifier = modifier
                .testTag("homeNav:home")
        ),
        NavigationItem(
            title = stringResource(R.string.menu_cart),
            icon = Icons.Default.ShoppingCart,
            screen = Screen.Cart,
            modifier = modifier
                .testTag("homeNav:cart")
        ),
        NavigationItem(
            title = stringResource(R.string.menu_profile),
            icon = Icons.Default.Person,
            screen = Screen.Profile,
            modifier = modifier
                .testTag("homeNav:about")
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
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
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
