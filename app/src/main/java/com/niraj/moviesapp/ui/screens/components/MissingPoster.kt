package com.niraj.moviesapp.ui.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.niraj.moviesapp.R


@Composable
fun MissingPoster(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.no_poster),
        contentDescription = stringResource(id = R.string.loading_error_content_description),
        contentScale = ContentScale.Crop,
        modifier = modifier
            .fillMaxSize()
            .alpha(0.5f)
    )
}

@Preview
@Composable
fun PreviewMissingPoster() {
    MissingPoster()
}