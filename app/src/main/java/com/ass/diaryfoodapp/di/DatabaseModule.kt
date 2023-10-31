package com.ass.diaryfoodapp.di

import android.content.Context
import androidx.room.Room
import com.ass.diaryfoodapp.db.AppFoodDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppFoodDB(@ApplicationContext context : Context) : AppFoodDB{
        return Room.databaseBuilder(context, AppFoodDB::class.java, "AppFoodDB").build()
    }
}