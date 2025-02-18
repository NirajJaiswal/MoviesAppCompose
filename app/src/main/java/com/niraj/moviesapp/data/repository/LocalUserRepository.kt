package com.niraj.moviesapp.data.repository

import androidx.annotation.WorkerThread
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.niraj.moviesapp.data.local.room.UserDao
import com.niraj.moviesapp.domain.model.FavoriteMovieId
import com.niraj.moviesapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class LocalUserRepository(
    private val userDao: UserDao,
    private val settingsDataStore: DataStore<Preferences>
) : UserRepository {

    override val allFavoriteMoviesIds: Flow<List<FavoriteMovieId>> = userDao.allFavoriteMoviesIds()

    @WorkerThread
    override suspend fun getAllFavoriteMoviesQuantity(): Flow<Int> =
        userDao.getAllFavoriteMoviesQuantity()

    @WorkerThread
    override suspend fun isMovieFavorite(movieId: Int): Boolean =
        userDao.isMovieFavorite(movieId) > 0

    @WorkerThread
    override suspend fun insertFavoriteMovie(movieId: FavoriteMovieId) =
        userDao.insertFavoriteMovie(movieId)

    @WorkerThread
    override suspend fun deleteFavoriteMovie(movieId: FavoriteMovieId) =
        userDao.deleteFavoriteMovie(movieId)

    @WorkerThread
    override suspend fun searchFavoriteMovies(searchTerm: String): Flow<List<FavoriteMovieId>> =
        userDao.searchFavoriteMovies(searchTerm)

    @WorkerThread
    override suspend fun <T> getPreference(key: Preferences.Key<T>, defaultValue: T): Flow<T> =
        settingsDataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            val result = preferences[key] ?: defaultValue
            result
        }

    @WorkerThread
    override suspend fun <T> insertPreference(key: Preferences.Key<T>, value: T) {
        settingsDataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    @WorkerThread
    override suspend fun <T> removePreference(key: Preferences.Key<T>) {
        settingsDataStore.edit { preferences ->
            preferences.remove(key)
        }
    }

    @WorkerThread
    override suspend fun clearAllPreference() {
        settingsDataStore.edit { preferences ->
            preferences.clear()
        }
    }

}