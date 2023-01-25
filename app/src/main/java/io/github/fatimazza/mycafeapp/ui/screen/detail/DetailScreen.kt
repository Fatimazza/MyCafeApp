package io.github.fatimazza.mycafeapp.ui.screen.detail

import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.fatimazza.mycafeapp.ui.components.ItemCounter
import io.github.fatimazza.mycafeapp.ui.components.OrderButton
import io.github.fatimazza.mycafeapp.ui.theme.MyCafeAppTheme
import io.github.fatimazza.mycafeapp.R

@Composable
fun DetailScreen() {
    DetailContent(
        R.drawable.menu_1
    )
}

@Composable
fun DetailContent(
    @DrawableRes image: Int,
    modifier: Modifier = Modifier
) {
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
                    modifier = modifier.height(400.dp)
                        .fillMaxWidth()
                        .border(
                            BorderStroke(4.dp, MaterialTheme.colors.primary),
                            RoundedCornerShape(
                                bottomStart = 20.dp, bottomEnd = 20.dp)
                        )
                        .clip(RoundedCornerShape(
                            bottomStart = 20.dp, bottomEnd = 20.dp))
                )
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    modifier = Modifier.padding(16.dp).clickable { }
                )
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .background(Color.LightGray)
        )
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ItemCounter(
                0,
                1,
                onItemIncreased = {  },
                onItemDecreased = {  },
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(bottom = 16.dp)
            )
            OrderButton(
                text = stringResource(R.string.add_to_cart, 0),
                enabled = true,
                onClick = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyCafeAppPreview() {
    MyCafeAppTheme {
        DetailContent(
            R.drawable.menu_1
        )
    }
}
