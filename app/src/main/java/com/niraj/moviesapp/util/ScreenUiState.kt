package com.niraj.moviesapp.util

sealed interface ScreenUiState<out T> {
    data class Success<out T>(val data: T) : ScreenUiState<T>
    object Loading : ScreenUiState<Nothing>
    data class Error(val message: String?) : ScreenUiState<Nothing>
}