package com.niraj.moviesapp.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.niraj.moviesapp.domain.model.Movie
import com.niraj.moviesapp.ui.theme.LightGray
import com.niraj.moviesapp.util.shimmerEffect


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieCard(
    movie: Movie,
    modifier: Modifier = Modifier,
    navigateToDetails: (Int) -> Unit
) {

    Card(
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        onClick = { navigateToDetails(movie.id) },
        modifier = modifier
            .height(230.dp)
            .width(170.dp)
    ) {

        Box(
            modifier = modifier.fillMaxSize()
        ) {

            MoviePosterImage(
                posterPath = movie.posterPath,
                gradientColor = MaterialTheme.colorScheme.surface
            )

            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(15.dp),
                contentAlignment = Alignment.BottomStart
            ) {

                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.TopStart
                ) {
                    CircularProgressBar(
                        percentage = movie.voteAverage,
                        radius = 20.dp,
                        fontSize = 15.sp
                    )
                }
                movie.title?.let {
                    Text(
                        text = it,
                        modifier = modifier.padding(bottom = 24.dp),
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
                movie.releaseDate?.let {
                    Text(
                        text = it,
                        color = LightGray,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

@Composable
fun LoadingMovieCard(modifier: Modifier = Modifier) {

    Card(
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        modifier = modifier
            .height(230.dp)
            .width(170.dp)
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .shimmerEffect()
        )
    }
}

@Composable
fun ErrorMovieCard(modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        modifier = modifier
            .height(230.dp)
            .width(170.dp)
    ) {
        Box(modifier = modifier.fillMaxSize()) {
            Box(
                modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface)
            )
            ErrorMessage(
                iconSize = 50.dp,
                textSize = 18.sp,
                errorColor = MaterialTheme.colorScheme.background,
                alpha = 1f
            )
        }
    }
}