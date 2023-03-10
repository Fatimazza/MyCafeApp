package io.github.fatimazza.mycafeapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.fatimazza.mycafeapp.ui.theme.MyCafeAppTheme

@Composable
fun OrderButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(50),
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun OrderButtonPreview() {
    MyCafeAppTheme {
        OrderButton(
            text = "Order",
            onClick = {}
        )
    }
}
