package com.niraj.moviesapp.ui.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.niraj.moviesapp.domain.model.Movie
import com.niraj.moviesapp.util.ScreenUiState


@Composable
fun MoviesLists(
    modifier: Modifier = Modifier,
    moviesUiStateList: List<Pair<Int, ScreenUiState<List<Movie>>>>,
    navigateToDetails: (Int) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(bottom = 20.dp)
    ) {
        itemsIndexed(moviesUiStateList) { _, list ->
            HeaderText(stringResource(list.first))
            Spacer(modifier.height(10.dp))
            RenderMoviesList(moviesUiState = list.second, navigateToDetails = navigateToDetails)
        }
    }
}


@Composable
fun HeaderText(text: String) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.onBackground,
        style = MaterialTheme.typography.headlineMedium.copy(
            fontWeight = FontWeight.Bold
        )
    )
}

@Composable
fun RenderMoviesList(
    moviesUiState: ScreenUiState<List<Movie>>,
    navigateToDetails: (Int) -> Unit
) {
    when (moviesUiState) {
        is ScreenUiState.Loading -> LoadingListOfMovies()
        is ScreenUiState.Success -> ListOfMovies(
            moviesUiState.data,
            navigateToDetails = navigateToDetails
        )

        is ScreenUiState.Error -> ErrorListOfMovies()
    }
}


@Composable
fun LoadingListOfMovies() {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
        items(15) {
            LoadingMovieCard()
        }
    }
}

@Composable
fun ListOfMovies(movies: List<Movie>, navigateToDetails: (Int) -> Unit) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
        itemsIndexed(movies) { _, movie ->
            MovieCard(movie = movie, navigateToDetails = navigateToDetails)
        }
    }
}


@Composable
fun ErrorListOfMovies() {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
        items(15) {
            ErrorMovieCard()
        }
    }
}