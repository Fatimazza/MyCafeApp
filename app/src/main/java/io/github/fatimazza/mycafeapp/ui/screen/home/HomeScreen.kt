package io.github.fatimazza.mycafeapp.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.fatimazza.mycafeapp.model.FakeMenuDataSource
import io.github.fatimazza.mycafeapp.model.Menu
import io.github.fatimazza.mycafeapp.ui.components.MenuItem
import io.github.fatimazza.mycafeapp.ui.theme.MyCafeAppTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    HomeContent(
        orderMenu = FakeMenuDataSource.dummyMenus,
        modifier = modifier
    )
}

@Composable
fun HomeContent(
    orderMenu: List<Menu>,
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
                image = data.image,
                title = data.title,
                price = data.price,
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
