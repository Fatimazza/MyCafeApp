package io.github.fatimazza.mycafeapp.ui.screen.cart

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.fatimazza.mycafeapp.R
import io.github.fatimazza.mycafeapp.di.Injection
import io.github.fatimazza.mycafeapp.ui.ViewModelFactory
import io.github.fatimazza.mycafeapp.ui.common.UiState
import io.github.fatimazza.mycafeapp.ui.components.CartItem
import io.github.fatimazza.mycafeapp.ui.components.OrderButton
import io.github.fatimazza.mycafeapp.ui.theme.MyCafeAppTheme

@Composable
fun CartScreen(
    viewModel: CartViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    onOrderButtonClicked: (String) -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAddedOrderMenus()
            }
            is UiState.Success -> {
                CartContent(
                    uiState.data,
                    onItemCountChanged = { menuId, count ->
                        viewModel.updateOrderMenu(menuId, count)
                    },
                    onOrderButtonClicked = onOrderButtonClicked,
                    viewModel = viewModel
                )
            }
            is UiState.Error -> {}
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CartContent(
    state: CartState,
    onItemCountChanged: (id: Long, count: Int) -> Unit,
    onOrderButtonClicked: (String) -> Unit,
    viewModel: CartViewModel,
    modifier: Modifier = Modifier.semantics {
        testTagsAsResourceId = true
    }
) {
    val cartItemNotFound by viewModel.cartItemNotFound
    val shareMessage = stringResource(
        R.string.share_message,
        state.orderMenu.count(),
        state.totalPrice
    )

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        TopAppBar(backgroundColor = MaterialTheme.colors.surface) {
            Text(
                text = stringResource(R.string.menu_cart),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .testTag("text:cartMenu"),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }
        OrderButton(
            text = stringResource(
                R.string.total_order,
                state.totalPrice
            ),
            enabled = state.orderMenu.isNotEmpty(),
            onClick = { onOrderButtonClicked(shareMessage) },
            modifier = modifier.padding(16.dp)
        )
        AnimatedVisibility(
            visible = cartItemNotFound
        ) {
            Row (
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.cart_no_item),
                    fontSize = 18.sp,
                    modifier = modifier.testTag("text:noItemFoodCart")
                )
            }
        }
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(state.orderMenu, key = { it.menu.id }) { item ->
                CartItem(
                    menuId = item.menu.id,
                    image = item.menu.image,
                    title = item.menu.title,
                    totalPrice = item.menu.price * item.count,
                    count = item.count,
                    onItemCountChanged = onItemCountChanged,
                )
                Spacer(
                    modifier = modifier.height(8.dp)
                )
                Divider()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyCafeAppPreview() {
    MyCafeAppTheme {
        CartScreen(
            onOrderButtonClicked = {}
        )
    }
}
