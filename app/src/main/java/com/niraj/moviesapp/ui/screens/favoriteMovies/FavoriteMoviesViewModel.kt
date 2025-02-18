package com.niraj.moviesapp.ui.screens.favoriteMovies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.niraj.moviesapp.domain.model.FavoriteMovieId
import com.niraj.moviesapp.domain.model.Movie
import com.niraj.moviesapp.domain.repository.MoviesRepository
import com.niraj.moviesapp.domain.repository.UserRepository
import com.niraj.moviesapp.util.ScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FavoriteMoviesViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _favoriteMoviesUiState =
        MutableStateFlow<ScreenUiState<List<Movie>>>(ScreenUiState.Loading)
    val favoriteMoviesUiState: Flow<ScreenUiState<List<Movie>>> = _favoriteMoviesUiState

    private val _searchedMoviesUiState =
        MutableStateFlow<ScreenUiState<List<Movie>>>(ScreenUiState.Loading)
    val searchedMoviesUiState: Flow<ScreenUiState<List<Movie>>> = _searchedMoviesUiState

    init {
        setupFavoriteMovies()
    }

    private fun setupFavoriteMovies() {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            _favoriteMoviesUiState.value = ScreenUiState.Error(throwable.message)
        }
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            _favoriteMoviesUiState.value = ScreenUiState.Loading
            val favoritesIdListDeferred = async {
                userRepository.allFavoriteMoviesIds
            }
            val favoritesIdList = favoritesIdListDeferred.await()
            withContext(Dispatchers.Main) {
                favoritesIdList.collect {
                    _favoriteMoviesUiState.value =
                        ScreenUiState.Success(getFavoriteMoviesListByIds(it))
                }
            }
        }
    }

    fun setSearchedMoviesList(searchTerm: String) {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            _favoriteMoviesUiState.value = ScreenUiState.Error(throwable.message)
        }
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            _searchedMoviesUiState.value = ScreenUiState.Loading
            val searchFavoritesIdListDeferred =
                async { userRepository.searchFavoriteMovies(searchTerm) }
            val searchFavoritesIdList =
                searchFavoritesIdListDeferred.await()
            withContext(Dispatchers.Main) {
                searchFavoritesIdList.collect {
                    _searchedMoviesUiState.value =
                        ScreenUiState.Success(getFavoriteMoviesListByIds(it))
                }
            }
        }
    }

    private suspend fun getFavoriteMoviesListByIds(
        favoriteMoviesIdList: List<FavoriteMovieId>
    ): MutableList<Movie> {
        val favoriteMoviesList = mutableListOf<Movie>()
        favoriteMoviesIdList.forEach {
            delay(300)
            favoriteMoviesList.add(moviesRepository.getMovieDetails(it.id))
        }
        return favoriteMoviesList
    }
}