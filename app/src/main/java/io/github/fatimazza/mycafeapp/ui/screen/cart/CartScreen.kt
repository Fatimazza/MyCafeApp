package io.github.fatimazza.mycafeapp.ui.screen.cart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.fatimazza.mycafeapp.R
import io.github.fatimazza.mycafeapp.ui.components.CartItem
import io.github.fatimazza.mycafeapp.ui.components.OrderButton
import io.github.fatimazza.mycafeapp.ui.theme.MyCafeAppTheme

@Composable
fun CartScreen(
) {
    
}

@Composable
fun CartContent(
    state: CartState,
    onItemCountChanged: (id: Long, count: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        TopAppBar(backgroundColor = MaterialTheme.colors.surface) {
            Text(
                text = stringResource(R.string.menu_cart),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
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
            onClick = {},
            modifier = Modifier.padding(16.dp)
        )
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
                Divider()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyCafeAppPreview() {
    MyCafeAppTheme {
        CartScreen()
    }
}
