package com.niraj.moviesapp.ui.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.niraj.moviesapp.R


@Composable
fun ErrorMessage(
    modifier: Modifier = Modifier,
    message: String = stringResource(R.string.loading_error_text),
    iconSize: Dp,
    textSize: TextUnit,
    errorColor: Color,
    alpha: Float
) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .alpha(alpha)
    ) {

        Icon(
            imageVector = Icons.Default.ErrorOutline,
            contentDescription = stringResource(R.string.loading_error_content_description),
            tint = errorColor,
            modifier = modifier
                .size(iconSize)
        )

        Text(
            message,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = errorColor,
                textAlign = TextAlign.Center,
                fontSize = textSize
            ),
            modifier = modifier.padding(top = 10.dp, start = 20.dp, end = 20.dp)
        )
    }
}


@Preview
@Composable
fun PreviewErrorMessage() {
    ErrorMessage(
        iconSize = 50.dp,
        textSize = 30.sp,
        errorColor = MaterialTheme.colorScheme.error,
        alpha = 0.8f
    )
}