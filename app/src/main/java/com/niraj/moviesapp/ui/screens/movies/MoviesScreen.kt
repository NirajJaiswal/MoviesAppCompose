package com.niraj.moviesapp.ui.screens.movies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.niraj.moviesapp.domain.model.Movie
import com.niraj.moviesapp.ui.screens.components.MoviesGrid
import com.niraj.moviesapp.ui.screens.components.MoviesLists
import com.niraj.moviesapp.ui.screens.components.SearchBar
import com.niraj.moviesapp.util.ScreenUiState
import kotlinx.coroutines.delay


@Composable
fun MoviesScreen(
    modifier: Modifier = Modifier,
    moviesUiStateList: List<Pair<Int, ScreenUiState<List<Movie>>>>,
    searchedMoviesUiStateList: ScreenUiState<List<Movie>>,
    setSearchedMoviesList: (String) -> Unit,
    navigateToDetails: (Int) -> Unit
) {

    val searchTerm = remember { mutableStateOf("") }

    LaunchedEffect(searchTerm.value) {
        delay(3000)
        setSearchedMoviesList(searchTerm.value)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 20.dp, start = 20.dp, end = 20.dp)
    ) {

        SearchBar(searchTerm)
        Spacer(modifier.height(20.dp))

        if (searchTerm.value.isEmpty()) {
            MoviesLists(moviesUiStateList = moviesUiStateList, navigateToDetails = navigateToDetails)
        } else {
            MoviesGrid(searchedMoviesUiStateList, navigateToDetails = navigateToDetails)
        }
    }
}