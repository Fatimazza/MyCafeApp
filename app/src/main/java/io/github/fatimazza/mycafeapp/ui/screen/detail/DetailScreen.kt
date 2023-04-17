package io.github.fatimazza.mycafeapp.ui.screen.detail

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.fatimazza.mycafeapp.R
import io.github.fatimazza.mycafeapp.di.Injection
import io.github.fatimazza.mycafeapp.ui.ViewModelFactory
import io.github.fatimazza.mycafeapp.ui.common.UiState
import io.github.fatimazza.mycafeapp.ui.components.ItemCounter
import io.github.fatimazza.mycafeapp.ui.components.OrderButton
import io.github.fatimazza.mycafeapp.ui.theme.MyCafeAppTheme

@Composable
fun DetailScreen(
    menuId: Long,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateBack: () -> Unit,
    navigateToCart: () -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getMenuById(menuId)
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    data.menu.image,
                    data.menu.title,
                    data.menu.price,
                    data.count,
                    onBackClick = navigateBack,
                    onAddToCart = { count ->
                        viewModel.addToCart(data.menu, count)
                        navigateToCart()
                    }
                )
            }
            is UiState.Error -> {}
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DetailContent(
    @DrawableRes image: Int,
    @StringRes title: Int,
    price: Int,
    count: Int,
    onBackClick: () -> Unit,
    onAddToCart: (count: Int) -> Unit,
    modifier: Modifier = Modifier.semantics {
        testTagsAsResourceId = true
    }
) {
    val context = LocalContext.current

    var totalPrice by rememberSaveable { mutableStateOf(0) }
    var orderCount by rememberSaveable { mutableStateOf(count) }

    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Box {
                Image(
                    painter = painterResource(image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .height(400.dp)
                        .fillMaxWidth()
                        .clip(
                            RoundedCornerShape(
                                bottomStart = 20.dp, bottomEnd = 20.dp
                            )
                        ).testTag("image:foodDetail")
                )
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { onBackClick() }
                        .testTag("icon:foodDetailBack")
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = context.getString(title),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h5.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                    modifier = Modifier
                        .testTag("text:foodDetailName")
                )
                Text(
                    text = stringResource(R.string.item_price, price),
                    style = MaterialTheme.typography.h6.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                    color = MaterialTheme.colors.secondary,
                    modifier = Modifier
                        .testTag("text:foodDetailPrice")
                )
                Text(
                    text = stringResource(R.string.lorem_ipsum),
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .testTag("text:foodDetailDesc")
                )
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(MaterialTheme.colors.secondary)
        )
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ItemCounter(
                1,
                orderCount,
                onItemIncreased = { orderCount++ },
                onItemDecreased = { if (orderCount > 0) orderCount-- },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 16.dp)
            )
            totalPrice = price * orderCount
            OrderButton(
                text = stringResource(R.string.add_to_cart, totalPrice),
                enabled = orderCount > 0,
                onClick = { onAddToCart(orderCount) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyCafeAppPreview() {
    MyCafeAppTheme {
        DetailContent(
            R.drawable.menu_1,
            R.string.food_sushi,
            15000,
            1,
            {},
            {}
        )
    }
}
