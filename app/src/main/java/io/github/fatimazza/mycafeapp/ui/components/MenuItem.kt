package io.github.fatimazza.mycafeapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.fatimazza.mycafeapp.R
import io.github.fatimazza.mycafeapp.ui.theme.MyCafeAppTheme

@Composable
fun MenuItem(
    image: Int,
    title: Int,
    price: Int,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .wrapContentSize(Alignment.Center)
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(170.dp)
                .border(
                    BorderStroke(4.dp, MaterialTheme.colors.primary),
                    RoundedCornerShape(15.dp)
                )
                .clip(RoundedCornerShape(15.dp))
        )
        Text(
            text = context.getString(title),
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.subtitle1.copy(
                fontWeight = FontWeight.ExtraBold
            ),
            modifier = modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = stringResource(R.string.item_price, price),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.subtitle2,
            color = MaterialTheme.colors.secondary,
            modifier = modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun RewardItemPreview() {
    MyCafeAppTheme {
        MenuItem(R.drawable.menu_1, R.string.food_sushi, 10000)
    }
}
