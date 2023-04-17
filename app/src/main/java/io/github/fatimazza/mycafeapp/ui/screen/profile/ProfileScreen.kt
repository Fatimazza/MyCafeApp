package io.github.fatimazza.mycafeapp.ui.screen.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.fatimazza.mycafeapp.R
import io.github.fatimazza.mycafeapp.ui.theme.MyCafeAppTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier.semantics {
        testTagsAsResourceId = true
    }
) {
    val context = LocalContext.current

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        TopAppBar(backgroundColor = MaterialTheme.colors.surface) {
            Text(
                text = stringResource(R.string.menu_profile),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .testTag("text:AboutMenu"),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(
                modifier = modifier.height(32.dp)
            )
            Text(
                text = context.getString(R.string.app_name),
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.Light
                ),
                modifier = modifier.align(Alignment.CenterHorizontally)
                    .testTag("text:AboutAppTitle")
            )
            Text(
                text = context.getString(R.string.developer_desc),
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.Light
                ),
                modifier = modifier.align(Alignment.CenterHorizontally)
                    .testTag("text:AboutDev")
            )
            Image(
                painter = painterResource(R.drawable.menu_1),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .border(
                        BorderStroke(4.dp, MaterialTheme.colors.primary),
                        CircleShape
                    )
                    .clip(CircleShape)
                    .size(220.dp)
                    .testTag("image:AboutDev")
            )
            Text(
                text = context.getString(R.string.developer_name),
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.Light
                ),
                modifier = modifier.align(Alignment.CenterHorizontally)
                    .testTag("text:AboutDevName")
            )
            Text(
                text = context.getString(R.string.developer_email),
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.Light
                ),
                modifier = modifier.align(Alignment.CenterHorizontally)
                    .testTag("text:AboutDevEmail")
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyCafeAppPreview() {
    MyCafeAppTheme {
        ProfileScreen()
    }
}
