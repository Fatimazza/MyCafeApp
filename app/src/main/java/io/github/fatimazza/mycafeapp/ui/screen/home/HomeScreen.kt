package io.github.fatimazza.mycafeapp.ui.screen.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.fatimazza.mycafeapp.R
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
    ),
    navigateToDetail: (Long) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllMenus()
            }
            is UiState.Success -> {
                HomeContent(
                    orderMenu = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                    viewModel = viewModel
                )
            }
            is UiState.Error -> {}
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomeContent(
    orderMenu: List<OrderMenu>,
    modifier: Modifier = Modifier.semantics {
        testTagsAsResourceId = true
    },
    navigateToDetail: (Long) -> Unit,
    viewModel: HomeViewModel,
) {
    val query by viewModel.query
    val context = LocalContext.current
    val searchNotFound by viewModel.searchNotFound

    viewModel.searchMenu(query, context)

    Column() {
        SearchBar(
            query = query,
            onQueryChange = { viewModel.searchMenu(it, context) },
            modifier = modifier
                .background(MaterialTheme.colors.primary)
        )
        AnimatedVisibility(
            visible = searchNotFound
        ) {
            Row (
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.search_no_result),
                    fontSize = 18.sp,
                    modifier = modifier.testTag("text:noResultFoodHome")
                )
            }
        }
        LazyVerticalGrid(
            columns = GridCells.Adaptive(160.dp),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier.testTag("gridList:foodHome")
        ) {
            items(orderMenu) { data ->
                MenuItem(
                    image = data.menu.image,
                    title = data.menu.title,
                    price = data.menu.price,
                    modifier = modifier.clickable {
                        navigateToDetail(data.menu.id)
                    }.testTag("menuItem:foodHome")
                )
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier.semantics {
        testTagsAsResourceId = true
    }
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        placeholder = {
            Text(stringResource(R.string.search_menu))
        },
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .clip(RoundedCornerShape(16.dp))
            .testTag("searchBar:foodHome")
    )
}

@Preview(showBackground = true)
@Composable
fun MyCafeAppPreview() {
    MyCafeAppTheme {
        HomeScreen(navigateToDetail = {})
    }
}
