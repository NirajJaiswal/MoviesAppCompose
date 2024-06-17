package com.niraj.moviesapp.di


import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.niraj.moviesapp.BuildConfig
import com.niraj.moviesapp.data.local.room.UserDatabase
import com.niraj.moviesapp.data.network.ApiKeyInterceptor
import com.niraj.moviesapp.data.network.MoviesApiService
import com.niraj.moviesapp.data.repository.LocalUserRepository
import com.niraj.moviesapp.data.repository.NetworkMoviesRepository
import com.niraj.moviesapp.domain.repository.MoviesRepository
import com.niraj.moviesapp.domain.repository.UserRepository
import com.niraj.moviesapp.util.MoviesConstant.BASE_URL
import com.niraj.moviesapp.util.MoviesConstant.SETTING_DATASTORE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val Context.settingsDataStore: DataStore<Preferences> by preferencesDataStore(name = SETTING_DATASTORE)


    @Provides
    @Singleton
    fun provideMoviesApiService(): MoviesApiService {

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor(BuildConfig.API_KEY))
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MoviesApiService::class.java)

    }

    @Provides
    @Singleton
    fun provideMoviesRepository(moviesApiService: MoviesApiService): MoviesRepository =
        NetworkMoviesRepository(moviesApiService)


    @Provides
    @Singleton
    fun provideUserRepository(context: Application): UserRepository = LocalUserRepository(
        UserDatabase.getDatabase(context).userDao(),
        context.settingsDataStore
    )

}