package com.mya.todolist.di

import android.content.Context
import androidx.room.Room
import com.mya.todolist.data.database.TodoDatabase
import com.mya.todolist.utils.Constants.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    // Provides a singleton instance of TodoDatabase
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context)=
        Room.databaseBuilder(context, TodoDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration().build()


    // Provides a singleton instance of TodoDao
    @Singleton
    @Provides
    fun todoDao(database: TodoDatabase) = database.todoDao()


}