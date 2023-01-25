package io.github.fatimazza.mycafeapp.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.fatimazza.mycafeapp.di.Injection
import io.github.fatimazza.mycafeapp.model.OrderMenu
import io.github.fatimazza.mycafeapp.ui.ViewModelFactory
import io.github.fatimazza.mycafeapp.ui.common.UiState
import io.github.fatimazza.mycafeapp.ui.components.MenuItem
import io.github.fatimazza.mycafeapp.ui.theme.MyCafeAppTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    )
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllMenus()
            }
            is UiState.Success -> {
                HomeContent(
                    orderMenu = uiState.data,
                    modifier = modifier
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    orderMenu: List<OrderMenu>,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(orderMenu) { data ->
            MenuItem(
                image = data.menu.image,
                title = data.menu.title,
                price = data.menu.price,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyCafeAppPreview() {
    MyCafeAppTheme {
        HomeScreen()
    }
}
