package io.github.fatimazza.mycafeapp.ui.screen.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.fatimazza.mycafeapp.ui.theme.MyCafeAppTheme

@Composable
fun DetailScreen() {
    DetailContent()
}

@Composable
fun DetailContent(
    modifier: Modifier = Modifier
) {

}

@Preview(showBackground = true)
@Composable
fun MyCafeAppPreview() {
    MyCafeAppTheme {
        DetailContent()
    }
}
