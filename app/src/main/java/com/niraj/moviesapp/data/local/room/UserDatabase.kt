package com.niraj.moviesapp.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.niraj.moviesapp.domain.model.FavoriteMovieId
import com.niraj.moviesapp.util.MoviesConstant


@Database(entities = [FavoriteMovieId::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    UserDatabase::class.java,
                    MoviesConstant.USER_DATABASE_NAME
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}