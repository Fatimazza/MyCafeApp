package io.github.fatimazza.mycafeapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.fatimazza.mycafeapp.R
import io.github.fatimazza.mycafeapp.ui.theme.MyCafeAppTheme
import io.github.fatimazza.mycafeapp.ui.theme.Shapes

@Composable
fun CartItem(
    menuId: Long,
    image: Int,
    title: Int,
    totalPrice: Int,
    count: Int,
    onItemCountChanged: (id: Long, count: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(90.dp)
                .border(
                    BorderStroke(2.dp, MaterialTheme.colors.primary),
                    RoundedCornerShape(15.dp)
                )
                .clip(RoundedCornerShape(15.dp).also { Shapes.small })
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .weight(1.0f)
        ) {
            Text(
                text = context.getString(title),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.subtitle1.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            Text(
                text = stringResource(
                    R.string.item_price,
                    totalPrice
                ),
                color = MaterialTheme.colors.secondary,
                style = MaterialTheme.typography.subtitle2,
            )
        }
        ItemCounter(
            orderId = menuId,
            orderCount = count,
            onItemIncreased = { onItemCountChanged(menuId, count + 1) },
            onItemDecreased = { onItemCountChanged(menuId, count - 1) },
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CartItemPreview() {
    MyCafeAppTheme {
        CartItem(
            4, R.drawable.menu_1, R.string.food_pizza, 4000, 0,
            onItemCountChanged = { menuId, count -> },
        )
    }
}
