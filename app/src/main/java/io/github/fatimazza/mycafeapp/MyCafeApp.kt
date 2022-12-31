package io.github.fatimazza.mycafeapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.github.fatimazza.mycafeapp.ui.theme.MyCafeAppTheme

@Composable
fun MyCafeApp() {
}

@Preview(showBackground = true)
@Composable
fun MyCafeAppPreview() {
    MyCafeAppTheme {
        MyCafeApp()
    }
}
